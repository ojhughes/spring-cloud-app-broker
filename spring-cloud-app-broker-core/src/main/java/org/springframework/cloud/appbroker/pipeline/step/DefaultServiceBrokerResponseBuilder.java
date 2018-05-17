package org.springframework.cloud.appbroker.pipeline.step;

import org.jooq.lambda.tuple.Tuple6;

import org.springframework.cloud.appbroker.pipeline.output.DeployedApp;
import org.springframework.cloud.appbroker.pipeline.output.DeployedServices;
import org.springframework.cloud.appbroker.pipeline.output.GeneratedCredentials;
import org.springframework.cloud.appbroker.pipeline.output.PersistResponse;
import org.springframework.cloud.appbroker.pipeline.output.TransformedParameters;
import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;
import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceResponse;

public class DefaultServiceBrokerResponseBuilder implements
	ServiceBrokerResponseBuilder<
		TransformedParameters,
		DeployedServices,
		DeployedApp,
		GeneratedCredentials,
		PersistResponse,
		CreateServiceInstanceResponse> {

	@Override
	public CreateServiceInstanceResponse apply(Tuple6<ServiceBrokerRequest, TransformedParameters, DeployedServices,
		DeployedApp, GeneratedCredentials, PersistResponse> previousStep) {
		return CreateServiceInstanceResponse.builder().build();
	}
}
