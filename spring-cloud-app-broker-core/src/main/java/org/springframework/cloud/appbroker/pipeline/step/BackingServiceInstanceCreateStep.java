package org.springframework.cloud.appbroker.pipeline.step;

import java.util.function.Function;

import org.jooq.lambda.tuple.Tuple2;
import org.jooq.lambda.tuple.Tuple3;

import org.springframework.cloud.appbroker.pipeline.output.DeployedServices;
import org.springframework.cloud.appbroker.pipeline.output.TransformedParameters;
import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;

public interface BackingServiceInstanceCreateStep<
	T extends TransformedParameters<?>,
	S extends DeployedServices<?>>

	extends Function<
		Tuple2<ServiceBrokerRequest, T>,
		Tuple3<ServiceBrokerRequest, T, S>> {
}
