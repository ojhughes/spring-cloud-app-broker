package org.springframework.cloud.appbroker.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.appbroker.pipeline.output.DeployedApp;
import org.springframework.cloud.appbroker.pipeline.output.DeployedServices;
import org.springframework.cloud.appbroker.pipeline.output.GeneratedCredentials;
import org.springframework.cloud.appbroker.pipeline.output.PersistResponse;
import org.springframework.cloud.appbroker.pipeline.output.TransformedParameters;
import org.springframework.cloud.appbroker.pipeline.step.AppDeployer;
import org.springframework.cloud.appbroker.pipeline.step.BackingServiceInstanceDeployer;
import org.springframework.cloud.appbroker.pipeline.step.ConfigurationMapper;
import org.springframework.cloud.appbroker.pipeline.step.CredentialGenerator;
import org.springframework.cloud.appbroker.pipeline.step.CredentialsMapper;
import org.springframework.cloud.appbroker.pipeline.step.ParameterTransformer;
import org.springframework.cloud.appbroker.pipeline.step.ParameterValidator;
import org.springframework.cloud.appbroker.pipeline.step.PersistenceService;
import org.springframework.cloud.appbroker.pipeline.step.ServiceBrokerResponseBuilder;
import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceResponse;

public class CreateServiceInstanceDefaultWorkflow implements CreateServiceInstanceAction<CreateServiceInstanceRequest, CreateServiceInstanceResponse> {

	private AppDeployer<TransformedParameters<?>, DeployedServices<?>, DeployedApp<?>> appDeployer;
	private BackingServiceInstanceDeployer<TransformedParameters<?>, DeployedServices<?>> backingServiceInstanceDeployer;
	private ConfigurationMapper<TransformedParameters<?>, DeployedServices<?>, DeployedApp<?>, GeneratedCredentials<?>, PersistResponse<?>> configurationMapper;
	private CredentialGenerator<TransformedParameters<?>, DeployedServices<?>, DeployedApp<?>, GeneratedCredentials<?>> credentialGenerator;
	private CredentialsMapper<TransformedParameters<?>, DeployedServices<?>, DeployedApp<?>, GeneratedCredentials<?>, PersistResponse<?>> credentialsMapper;
	private ParameterTransformer<TransformedParameters<?>> parameterTransformer;
	private ParameterValidator parameterValidator;
	private PersistenceService<TransformedParameters<?>, DeployedServices<?>, DeployedApp<?>, GeneratedCredentials<?>, PersistResponse<?>> persistServiceInstanceStateStep;
	private ServiceBrokerResponseBuilder<TransformedParameters<?>, DeployedServices<?>, DeployedApp<?>, GeneratedCredentials<?>, PersistResponse<?>, CreateServiceInstanceResponse> serviceBrokerResponseBuilder;

	@Autowired
	public CreateServiceInstanceDefaultWorkflow(ParameterValidator parameterValidator,
												ParameterTransformer<TransformedParameters<?>> parameterTransformer,
												AppDeployer<TransformedParameters<?>, DeployedServices<?>, DeployedApp<?>> appDeployer,
												BackingServiceInstanceDeployer<TransformedParameters<?>, DeployedServices<?>> backingServiceInstanceDeployer,
												CredentialGenerator<TransformedParameters<?>, DeployedServices<?>, DeployedApp<?>, GeneratedCredentials<?>> credentialGenerator,
												PersistenceService<TransformedParameters<?>, DeployedServices<?>, DeployedApp<?>, GeneratedCredentials<?>, PersistResponse<?>> persistServiceInstanceStateStep,
												CredentialsMapper<TransformedParameters<?>, DeployedServices<?>, DeployedApp<?>, GeneratedCredentials<?>, PersistResponse<?>> credentialsMapper,
												ConfigurationMapper<TransformedParameters<?>, DeployedServices<?>, DeployedApp<?>, GeneratedCredentials<?>, PersistResponse<?>> configurationMapper,
												ServiceBrokerResponseBuilder<TransformedParameters<?>, DeployedServices<?>, DeployedApp<?>, GeneratedCredentials<?>, PersistResponse<?>, CreateServiceInstanceResponse> serviceBrokerResponseBuilder) {

		this.parameterValidator = parameterValidator;
		this.parameterTransformer = parameterTransformer;
		this.appDeployer = appDeployer;
		this.backingServiceInstanceDeployer = backingServiceInstanceDeployer;
		this.credentialGenerator = credentialGenerator;
		this.persistServiceInstanceStateStep = persistServiceInstanceStateStep;
		this.credentialsMapper = credentialsMapper;
		this.configurationMapper = configurationMapper;
		this.serviceBrokerResponseBuilder = serviceBrokerResponseBuilder;
	}

	@Override
	public CreateServiceInstanceResponse perform(CreateServiceInstanceRequest requestData) {
		return parameterValidator
			.andThen(parameterTransformer)
			.andThen(backingServiceInstanceDeployer)
			.andThen(appDeployer)
			.andThen(credentialGenerator)
			.andThen(persistServiceInstanceStateStep)
			.andThen(credentialsMapper)
			.andThen(configurationMapper)
			.andThen(serviceBrokerResponseBuilder)
			.apply(requestData);
	}

}
