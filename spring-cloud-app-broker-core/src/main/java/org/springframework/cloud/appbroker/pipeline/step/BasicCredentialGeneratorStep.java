package org.springframework.cloud.appbroker.pipeline.step;

import java.io.Serializable;

import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple4;
import org.jooq.lambda.tuple.Tuple5;

import org.springframework.cloud.appbroker.pipeline.output.DeployedApp;
import org.springframework.cloud.appbroker.pipeline.output.DeployedServices;
import org.springframework.cloud.appbroker.pipeline.output.GeneratedCredentials;
import org.springframework.cloud.appbroker.pipeline.output.GeneratedSpringSecurityBasicCredentials;
import org.springframework.cloud.appbroker.pipeline.output.TransformedParameters;
import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;

public class BasicCredentialGeneratorStep<T, S extends Serializable, A extends Serializable, G extends Serializable> implements
	CredentialGeneratorStep<
		TransformedParameters<?>,
		DeployedServices<?>,
		DeployedApp<?>,
		GeneratedCredentials<?>> {

	@Override
	public Tuple5<ServiceBrokerRequest, TransformedParameters<?>, DeployedServices<?>, DeployedApp<?>, GeneratedCredentials<?>>
	apply(Tuple4<ServiceBrokerRequest, TransformedParameters<?>, DeployedServices<?>, DeployedApp<?>> previousStep) {
		return Tuple.tuple(previousStep.v1, previousStep.v2, previousStep.v3, previousStep.v4, new GeneratedSpringSecurityBasicCredentials());
	}
}
