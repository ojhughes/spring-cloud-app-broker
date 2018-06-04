package org.springframework.cloud.appbroker.pipeline.step;

import java.io.Serializable;

import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;
import org.jooq.lambda.tuple.Tuple3;

import org.springframework.cloud.appbroker.pipeline.output.CloudFoundryDeployedServices;
import org.springframework.cloud.appbroker.pipeline.output.DeployedServices;
import org.springframework.cloud.appbroker.pipeline.output.TransformedParameters;
import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;

public class CloudFoundryBackingServiceInstanceCreateStep<T, S extends Serializable> implements
	BackingServiceInstanceCreateStep<
		TransformedParameters<?>,
		DeployedServices<?>> {

	@Override
	public Tuple3<ServiceBrokerRequest, TransformedParameters<?>, DeployedServices<?>>
	apply(Tuple2<ServiceBrokerRequest, TransformedParameters<?>> previousStep) {
		return Tuple.tuple(previousStep.v1, previousStep.v2, new CloudFoundryDeployedServices());
	}
}
