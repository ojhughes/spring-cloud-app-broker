#!/usr/bin/env bash
set -euo pipefail
readonly SCRIPT_DIR=$(readlink -m $(dirname $0))
readonly CI_DIR=$(dirname "${SCRIPT_DIR}")
readonly BBL_VERSION=v6.9.16
readonly BBL_GCP_SERVICE_ACCOUNT_KEY="${CI_DIR}/gcp-key.json"

declare -Ar BBL_STATE_FILE_PATHS=(
        ["BBL_${BBL_ENV_SUFFIX}_BBL_STATE_JSON"]="${SCRIPT_DIR}/bbl-state.json"
        ["BBL_${BBL_ENV_SUFFIX}_BBL_TFVARS"]="${SCRIPT_DIR}/vars/bbl.tfvars"
        ["BBL_${BBL_ENV_SUFFIX}_BOSH_STATE_JSON"]="${SCRIPT_DIR}/vars/bosh-state.json"
        ["BBL_${BBL_ENV_SUFFIX}_CLOUD_CONFIG_VARS_YML"]="${SCRIPT_DIR}/vars/cloud-config-vars.yml"
        ["BBL_${BBL_ENV_SUFFIX}_DIRECTOR_VARS_FILE_YML"]="${SCRIPT_DIR}/vars/director-vars-file.yml"
        ["BBL_${BBL_ENV_SUFFIX}_DIRECTOR_VARS_STORE_YML"]="${SCRIPT_DIR}/vars/director-vars-store.yml"
        ["BBL_${BBL_ENV_SUFFIX}_JUMPBOX_STATE_JSON"]="${SCRIPT_DIR}/vars/jumpbox-state.json"
        ["BBL_${BBL_ENV_SUFFIX}_JUMPBOX_VARS_FILE_YML"]="${SCRIPT_DIR}/vars/jumpbox-vars-file.yml"
        ["BBL_${BBL_ENV_SUFFIX}_JUMPBOX_VARS_STORE_YML"]="${SCRIPT_DIR}/vars/jumpbox-vars-store.yml"
        ["BBL_${BBL_ENV_SUFFIX}_TERRAFORM_TFSTATE"]="${SCRIPT_DIR}/vars/terraform.tfstate"
    )

validate(){
	source_environment_variables
	[[ $(type bbl) ]] || (echo "bbl tool not installed" >&2 ; bbl_usage ; exit 2)
	[[ $(type gcloud) ]] || (echo "gcloud tool not installed" >&2 ; gcloud_usage ; exit 2)

	has_ip_cidr_already_been_used
	is_ip_cidr_valid
}



bbl_usage() {
    cat <<- EOF
Install circle command line tool to run local builds:
    "curl -o /usr/local/bin/bbl https://github.com/cloudfoundry/bosh-bootloader/releases/download/${BBL_VERSION}/bbl-${BBL_VERSION}_osx && \
    chmod +x /usr/local/bin/bbl"
EOF
}

gcloud_usage() {
    cat <<- EOF
Install Google Cloud SDK, see https://cloud.google.com/sdk/docs/quickstart-macos
EOF
}

has_ip_cidr_already_been_used(){
	echo "${GCP_KEY_BASE64}" | base64 -d > "${CI_DIR}/gcp-key.json"
	gcloud auth activate-service-account --key-file="${BBL_GCP_SERVICE_ACCOUNT_KEY}"
	gcloud config set project "${BBL_GCP_PROJECT_ID}"
	$(gcloud compute networks subnets list --filter="name :(appbroker*)" --format=json \
	| jq -e --arg IP_CIDR "$INTERNAL_NETWORK_CIDR" '. | map(.ipCidrRange) | index($IP_CIDR) | not')
}

is_ip_cidr_valid(){
	python3  -c "import ipaddress, sys; ipaddress.ip_network(sys.argv[1])" "${INTERNAL_NETWORK_CIDR}"
}

calculate_jumpbox_ip(){
	python3  -c "import ipaddress, sys; n = ipaddress.ip_network(sys.argv[1]); print(n[5])" "${INTERNAL_NETWORK_CIDR}"
}

calculate_director_ip(){
	python3  -c "import ipaddress, sys; n = ipaddress.ip_network(sys.argv[1]); print(n[6])" "${INTERNAL_NETWORK_CIDR}"
}

source_environment_variables(){
	[[ ! -f "${CI_DIR}/bbl-env-creds.sh" ]] && printf "Download .envrc from LastPass using \"lpass show --notes 6073833788799831660\" in directory ${CI_DIR} before running script" && exit 1
	source "${CI_DIR}/bbl-env-creds.sh"
}

create_bbl_state_dir() {
	mkdir -p $(get_bbl_state_dir)
}

create_new_bbl_plan() {
	bbl plan --name get -s $(get_bbl_state_dir)
}

copy_plan_patch_files() {
	cp -r "${CI_DIR}/appbroker-bbl-plan-patch/." $(get_bbl_state_dir)
}

interpolate_terraform_template_file() {
	local -r internal_cidr="${1}"
	sed -e "s|<%= directorIp %>|$(calculate_director_ip)|;" "$(get_bbl_state_dir)/terraform/bosh-lite_override.tf.tmpl" > "$(get_bbl_state_dir)/terraform/bosh-lite_override.tf"
	sed -e "s|<%= internalCidr %>|${internal_cidr}|;" "$(get_bbl_state_dir)/terraform/bbl-template.tf.tmpl" > "$(get_bbl_state_dir)/terraform/bbl-template.tf"
}

create_director_and_jumpbox(){
	DIRECTOR_INTERNAL_IP=$(calculate_director_ip) \
	JUMPBOX_INTERNAL_IP=$(calculate_jumpbox_ip) \
	bbl up -s $(get_bbl_state_dir)
}

source_bbl_environment() {
	bbl print-env > bbl-env.sh && source bbl-env.sh
}

clone_cf_deployment(){
	git clone git@github.com:cloudfoundry/cf-deployment.git "$(get_bbl_state_dir)/cf-deployment"
}

deploy_cf(){
	bosh update-runtime-config "$(get_bbl_state_dir)/bosh-deployment/runtime-configs/dns.yml" --name dns
	local -r stemcell_version=$(bosh interpolate cf-deployment/cf-deployment.yml --path /stemcells/alias=default/version)
	bosh upload-stemcell https://bosh.io/d/stemcells/bosh-warden-boshlite-ubuntu-trusty-go_agent?v=${stemcell_version}
	bosh -n -d cf deploy cf-deployment/cf-deployment.yml -o cf-deployment/operations/bosh-lite.yml -v system_domain="$(get_bbl_env_name).cf-app.com"
}

update_dns(){
	local -r director_external_ip=$(awk '/external_ip:/ {print $2}' < "$(get_bbl_state_dir)/vars/director-vars-file.yml")
	"${SCRIPT_DIR}/update-route53.sh" $(get_bbl_env_name) "${director_external_ip}"
}

base64_encode_bbl_state(){
	declare -n bbl_state_file_paths="${1}"
	local -r upload_env_vars_to_circle="${2}" upload_env_vars_to_lastpass="${3}"
    for var_name in "${!bbl_state_file_paths[@]}"; do
    	local base64_file_contents=$(base64 "${!bbl_state_file_paths[@]}")
        printf "export ${var_name}=${base64_file_contents}\n" > "$(get_bbl_state_dir)/.envrc"
    done
}

base64_decode_bbl_state(){
	declare -n bbl_state_file_paths="${1}"
	for var_name in "${!bbl_state_file_paths[@]}"; do
    	local decoded_file_contents=$(base64 -d "${!bbl_state_file_paths[@]}")
        printf "${decoded_file_contents}" > "$(get_bbl_state_dir)/${!bbl_state_file_paths[@]}"
    done
}

upload_environment_variables_to_lastpass(){
	echo
}

upload_environment_variables_to_circleci(){
	echo
}

get_bbl_state_dir(){
	local -r bbl_env_suffix="${1}"
	echo "${CI_DIR}/bosh-env-${bbl_env_suffix}/bbl-state"
}

get_bbl_env_name(){
	local -r bbl_env_suffix="${1}"
	echo "appbroker-${bbl_env_suffix}"
}

generate_bbl_state_dir_for_environment(){
	local -r bbl_env_suffix="${1}"
	declare -A bbl_env_scoped_variables=("${bbl_env_suffix}"="BBL_${bbl_env_suffix}_INTERNAL_NETWORK_CIDR")
	create_bbl_state_dir
	copy_plan_patch_files
	interpolate_terraform_template_file ${bbl_env_scoped_variables[${bbl_env_suffix}]}
	base64_decode_bbl_state "${BBL_STATE_FILE_PATHS}"
}

create_new_bbl_bosh_environment(){
	local -r bbl_env_suffix="${1}" internal_network_cidr="${2}" upload_env_vars_to_circleci="${3}" upload_env_vars_to_lastpass="{$4}"
	validate
	create_bbl_state_dir
	create_new_bbl_plan
	copy_plan_patch_files
	interpolate_terraform_template_file "${internal_network_cidr}"
	create_director_and_jumpbox
	source_bbl_environment
	deploy_cf
	update_dns
	base64_encode_bbl_state	"${BBL_STATE_FILE_PATHS}" "${upload_env_vars_to_circleci}" "${upload_env_vars_to_lastpass}"
}

process_command(){
	local -r arg="${1}"
	local OPTIND
	local upload_env_vars_to_circleci=false
	local upload_env_vars_to_lastpass=false
	local internal_network_cidr
	local bbl_env_suffix

	case "${arg}" in
		create-new-environment)
			shift
			while getopts ":ei:b:" FOUND "${@}";
			do
				case ${FOUND} in
					e) upload_env_vars_to_circleci=true
						;;
					l) upload_env_vars_to_lastpass=true
						;;
					i) internal_network_cidr="${OPTARG}"
						;;
					b) bbl_env_suffix="${OPTARG}"
						;;
					\:) printf "argument missing from -%s option\n" $OPTARG
						usage
						exit 2
						;;
					\?) printf "unknown option: -%s\n" $OPTARG
						usage
						exit 2
						;;
				esac >&2
			done
			shift $(($OPTIND - 1))
			: ${bbl_env_suffix:?"Required Env Variable not found!"}
			: ${internal_network_cidr:?"Required Env Variable not found!"}
			create_new_bbl_bosh_environment "${bbl_env_suffix}" "${internal_network_cidr}" "${upload_env_vars_to_circleci}" "${upload_env_vars_to_lastpass}"
			;;

		generate-bbl-state-directory)
			shift
			while getopts ":b:" FOUND "${@}";
			do
				case ${FOUND} in
					b) bbl_env_suffix="${OPTARG}"
						;;
					\:) printf "argument missing from -%s option\n" $OPTARG
						usage
						exit 2
						;;
					\?) printf "unknown option: -%s\n" $OPTARG
						usage
						exit 2
						;;
				esac >&2
			done
			shift $(($OPTIND - 1))
			: ${bbl_env_suffix:?"Required Env Variable not found!"}
			generate_bbl_state_dir_for_environment "${bbl_env_suffix}"
			;;
    esac

}

process_command "${@}"