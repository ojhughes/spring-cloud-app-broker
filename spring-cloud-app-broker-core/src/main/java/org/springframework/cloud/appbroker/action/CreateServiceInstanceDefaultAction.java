package org.springframework.cloud.appbroker.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.appbroker.pipeline.output.DeployedApp;
import org.springframework.cloud.appbroker.pipeline.output.DeployedServices;
import org.springframework.cloud.appbroker.pipeline.output.GeneratedCredentials;
import org.springframework.cloud.appbroker.pipeline.output.PersistResponse;
import org.springframework.cloud.appbroker.pipeline.output.TransformedParameters;
import org.springframework.cloud.appbroker.pipeline.step.AppDeploymentStep;
import org.springframework.cloud.appbroker.pipeline.step.BackingServiceInstanceCreateStep;
import org.springframework.cloud.appbroker.pipeline.step.ConfigurationMapperStep;
import org.springframework.cloud.appbroker.pipeline.step.CredentialGeneratorStep;
import org.springframework.cloud.appbroker.pipeline.step.CredentialsMapperStep;
import org.springframework.cloud.appbroker.pipeline.step.ParameterTransformerStep;
import org.springframework.cloud.appbroker.pipeline.step.ParameterValidatorStep;
import org.springframework.cloud.appbroker.pipeline.step.PersistenceStep;
import org.springframework.cloud.appbroker.pipeline.step.ServiceBrokerResponseBuilder;
import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceResponse;
import org.springframework.stereotype.Component;

@Component
public class CreateServiceInstanceDefaultAction implements CreateServiceInstanceAction<CreateServiceInstanceRequest, CreateServiceInstanceResponse> {

	private ParameterValidatorStep parameterValidatorStep;
	private AppDeploymentStep<TransformedParameters, DeployedServices, DeployedApp> appDeploymentStep;
	private BackingServiceInstanceCreateStep<TransformedParameters, DeployedServices> backingServiceInstanceCreateStep;
	private ConfigurationMapperStep<TransformedParameters, DeployedServices, DeployedApp, GeneratedCredentials, PersistResponse> configurationMapperStep;
	private CredentialGeneratorStep<TransformedParameters, DeployedServices, DeployedApp, GeneratedCredentials> credentialGeneratorStep;
	private CredentialsMapperStep<TransformedParameters, DeployedServices, DeployedApp, GeneratedCredentials, PersistResponse> credentialsMapperStep;
	private ParameterTransformerStep<TransformedParameters> parameterTransformerStep;
	private PersistenceStep<TransformedParameters, DeployedServices, DeployedApp, GeneratedCredentials, PersistResponse> persistServiceInstanceStateStep;
	private ServiceBrokerResponseBuilder<TransformedParameters, DeployedServices, DeployedApp, GeneratedCredentials, PersistResponse, CreateServiceInstanceResponse> serviceBrokerResponseBuilder;

	@Autowired
	public CreateServiceInstanceDefaultAction(ParameterValidatorStep parameterValidatorStep,
											  ParameterTransformerStep<TransformedParameters> parameterTransformerStep,
											  AppDeploymentStep<TransformedParameters, DeployedServices, DeployedApp> appDeploymentStep,
											  BackingServiceInstanceCreateStep<TransformedParameters, DeployedServices> backingServiceInstanceCreateStep,
											  CredentialGeneratorStep<TransformedParameters, DeployedServices, DeployedApp, GeneratedCredentials> credentialGeneratorStep,
											  PersistenceStep<TransformedParameters, DeployedServices, DeployedApp, GeneratedCredentials, PersistResponse> persistServiceInstanceStateStep,
											  CredentialsMapperStep<TransformedParameters, DeployedServices, DeployedApp, GeneratedCredentials, PersistResponse> credentialsMapperStep,
											  ConfigurationMapperStep<TransformedParameters, DeployedServices, DeployedApp, GeneratedCredentials, PersistResponse> configurationMapperStep,
											  ServiceBrokerResponseBuilder<TransformedParameters, DeployedServices, DeployedApp, GeneratedCredentials, PersistResponse, CreateServiceInstanceResponse> serviceBrokerResponseBuilder) {

		this.parameterValidatorStep = parameterValidatorStep;
		this.parameterTransformerStep = parameterTransformerStep;
		this.appDeploymentStep = appDeploymentStep;
		this.backingServiceInstanceCreateStep = backingServiceInstanceCreateStep;
		this.credentialGeneratorStep = credentialGeneratorStep;
		this.persistServiceInstanceStateStep = persistServiceInstanceStateStep;
		this.credentialsMapperStep = credentialsMapperStep;
		this.configurationMapperStep = configurationMapperStep;
		this.serviceBrokerResponseBuilder = serviceBrokerResponseBuilder;
	}

	@Override
	public CreateServiceInstanceResponse perform(CreateServiceInstanceRequest requestData) {
		return parameterValidatorStep
			.andThen(parameterTransformerStep)
			.andThen(backingServiceInstanceCreateStep)
			.andThen(appDeploymentStep)
			.andThen(credentialGeneratorStep)
			.andThen(persistServiceInstanceStateStep)
			.andThen(credentialsMapperStep)
			.andThen(configurationMapperStep)
			.andThen(serviceBrokerResponseBuilder)
			.apply(requestData);
	}

}
