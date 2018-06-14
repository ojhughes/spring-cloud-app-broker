package org.springframework.cloud.appbroker.pipeline.step;

import java.util.function.Function;

import org.jooq.lambda.tuple.Tuple6;

import org.springframework.cloud.appbroker.pipeline.output.DeployedApp;
import org.springframework.cloud.appbroker.pipeline.output.DeployedServices;
import org.springframework.cloud.appbroker.pipeline.output.GeneratedCredentials;
import org.springframework.cloud.appbroker.pipeline.output.PersistResponse;
import org.springframework.cloud.appbroker.pipeline.output.TransformedParameters;
import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;

public interface CredentialsMapper<
	T extends TransformedParameters<?>,
	S extends DeployedServices<?>,
	A extends DeployedApp<?>,
	G extends GeneratedCredentials<?>,
	P extends PersistResponse<?>>

	extends Function<
		Tuple6<ServiceBrokerRequest, T, S, A, G, P>,
		Tuple6<ServiceBrokerRequest, T, S, A, G, P>> {
}
