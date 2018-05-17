package org.springframework.cloud.appbroker.pipeline.step;

import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple6;

import org.springframework.cloud.appbroker.pipeline.output.DeployedApp;
import org.springframework.cloud.appbroker.pipeline.output.DeployedServices;
import org.springframework.cloud.appbroker.pipeline.output.GeneratedCredentials;
import org.springframework.cloud.appbroker.pipeline.output.PersistResponse;
import org.springframework.cloud.appbroker.pipeline.output.TransformedParameters;
import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;

public class SpringJsonConfigMapperStep implements
	ConfigurationMapperStep<
		TransformedParameters,
		DeployedServices,
		DeployedApp,
		GeneratedCredentials,
		PersistResponse> {

	@Override
	public Tuple6<ServiceBrokerRequest, TransformedParameters, DeployedServices, DeployedApp, GeneratedCredentials, PersistResponse>
	apply(Tuple6<ServiceBrokerRequest, TransformedParameters, DeployedServices, DeployedApp, GeneratedCredentials, PersistResponse> previousStep) {
		return Tuple.tuple(previousStep.v1, previousStep.v2, previousStep.v3, previousStep.v4, previousStep.v5, previousStep.v6);
	}
}
