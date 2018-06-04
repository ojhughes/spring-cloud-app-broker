package org.springframework.cloud.appbroker.pipeline.step;

import java.util.function.Function;

import org.jooq.lambda.tuple.Tuple3;
import org.jooq.lambda.tuple.Tuple4;

import org.springframework.cloud.appbroker.pipeline.output.DeployedApp;
import org.springframework.cloud.appbroker.pipeline.output.DeployedServices;
import org.springframework.cloud.appbroker.pipeline.output.TransformedParameters;
import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;


public interface AppDeploymentStep<
	T extends TransformedParameters<?>,
	S extends DeployedServices<?>,
	A extends DeployedApp<?>>

	extends Function<
		Tuple3<ServiceBrokerRequest, T, S>,
		Tuple4<ServiceBrokerRequest, T, S, A>> {
}
