package org.springframework.cloud.appbroker.pipeline.step;

import java.util.function.Function;

import org.jooq.lambda.tuple.Tuple4;
import org.jooq.lambda.tuple.Tuple5;

import org.springframework.cloud.appbroker.pipeline.output.DeployedApp;
import org.springframework.cloud.appbroker.pipeline.output.DeployedServices;
import org.springframework.cloud.appbroker.pipeline.output.GeneratedCredentials;
import org.springframework.cloud.appbroker.pipeline.output.TransformedParameters;
import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;

public interface CredentialGeneratorStep<
	T extends TransformedParameters<?>,
	S extends DeployedServices<?>,
	A extends DeployedApp<?>,
	G extends GeneratedCredentials<?>>

	extends Function<
		Tuple4<ServiceBrokerRequest, T, S, A>,
		Tuple5<ServiceBrokerRequest, T, S, A, G>> {
}
