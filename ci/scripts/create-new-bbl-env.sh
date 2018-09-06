#!/usr/bin/env bash
set -euo pipefail
readonly SCRIPT_DIR=$(readlink -m $(dirname $0))
readonly CI_DIR=$(dirname "${SCRIPT_DIR}")
readonly BBL_VERSION=v6.9.16
readonly BBL_ENV_SUFFIX="${1}"
readonly IP_CIDR="${2}"
readonly BBL_ENV_NAME="appbroker-${BBL_ENV_SUFFIX}"
readonly BBL_STATE_DIR="${CI_DIR}/bosh-env-${BBL_ENV_SUFFIX}/bbl-state"
readonly CF_DOMAIN="${BBL_ENV_NAME}.cf-app.com"
export BBL_GCP_SERVICE_ACCOUNT_KEY="${CI_DIR}/gcp-key.json"

validate(){
	source_environment_variables
	[[ $(type bbl) ]] || (echo "bbl tool not installed" >&2 ; bbl_usage ; exit 2)
	[[ $(type gcloud) ]] || (echo "gcloud tool not installed" >&2 ; gcloud_usage ; exit 2)
	: ${BBL_ENV_SUFFIX:?"Required Env Variable not found!"}
	: ${IP_CIDR:?"Required Env Variable not found!"}

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
	| jq -e --arg IP_CIDR "$IP_CIDR" '. | map(.ipCidrRange) | index($IP_CIDR) | not')
}

is_ip_cidr_valid(){
	python3  -c "import ipaddress, sys; ipaddress.ip_network(sys.argv[1])" "${IP_CIDR}"
}

calculate_jumpbox_ip(){
	python3  -c "import ipaddress, sys; n = ipaddress.ip_network(sys.argv[1]); print(n[5])" "${IP_CIDR}"
}

calculate_director_ip(){
	python3  -c "import ipaddress, sys; n = ipaddress.ip_network(sys.argv[1]); print(n[6])" "${IP_CIDR}"
}

source_environment_variables(){
	[[ ! -f "${CI_DIR}/bbl-env-creds.sh" ]] && printf "Download .envrc from LastPass using \"lpass show --notes 6073833788799831660\" in directory ${CI_DIR} before running script" && exit 1
	source "${CI_DIR}/bbl-env-creds.sh"
}

create_bbl_state_dir() {
	mkdir -p "${BBL_STATE_DIR}"
}

create_new_bbl_plan() {
	bbl plan --name "${BBL_ENV_NAME}" -s "${BBL_STATE_DIR}"
}

copy_plan_patch_files() {
	cp -r "${CI_DIR}/appbroker-bbl-plan-patch/." "${BBL_STATE_DIR}"
}

interpolate_terraform_template_file() {
	sed -e "s|<%= directorIp %>|$(calculate_director_ip)|;" "${BBL_STATE_DIR}/terraform/bosh-lite_override.tf.tmpl" > "${BBL_STATE_DIR}/terraform/bosh-lite_override.tf"
}

create_director_and_jumpbox(){
	DIRECTOR_INTERNAL_IP=$(calculate_director_ip) \
	JUMPBOX_INTERNAL_IP=$(calculate_jumpbox_ip) \
	bbl up -s "${BBL_STATE_DIR}"
}

source_bbl_environment() {
	bbl print-env > bbl-env.sh && source bbl-env.sh
}

clone_cf_deployment(){
	git clone git@github.com:cloudfoundry/cf-deployment.git "${BBL_STATE_DIR}/cf-deployment"
}

deploy_cf(){
	bosh update-runtime-config "${BBL_STATE_DIR}bosh-deployment/runtime-configs/dns.yml" --name dns
	local -r stemcell_version=$(bosh interpolate cf-deployment/cf-deployment.yml --path /stemcells/alias=default/version)
	bosh upload-stemcell https://bosh.io/d/stemcells/bosh-warden-boshlite-ubuntu-trusty-go_agent?v=${STEMCELL_VERSION}
	bosh -n -d cf deploy cf-deployment/cf-deployment.yml -o cf-deployment/operations/bosh-lite.yml -v system_domain="${CF_DOMAIN}"
}

update_dns(){
	local -r director_external_ip=$(awk '/external_ip:/ {print $2}' < "${BBL_STATE_DIR}/vars/director-vars-file.yml")
	"${SCRIPT_DIR}/update-route53.sh" "${BBL_ENV_NAME}" "${director_external_ip}"
}

base64_encode_bbl_state(){
	echo
}

base64_decode_bbl_state(){
	echo
}

process_command(){
	validate
	create_bbl_state_dir
	create_new_bbl_plan
	copy_plan_patch_files
	interpolate_terraform_template_file
	create_director_and_jumpbox
	source_bbl_environment
	deploy_cf
	update_dns
}

process_command