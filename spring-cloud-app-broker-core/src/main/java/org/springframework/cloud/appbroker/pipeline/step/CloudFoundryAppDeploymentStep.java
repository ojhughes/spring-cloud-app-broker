package org.springframework.cloud.appbroker.pipeline.step;

import java.io.Serializable;

import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple3;
import org.jooq.lambda.tuple.Tuple4;

import org.springframework.cloud.appbroker.pipeline.output.CloudFoundryDeployedApp;
import org.springframework.cloud.appbroker.pipeline.output.DeployedApp;
import org.springframework.cloud.appbroker.pipeline.output.DeployedServices;
import org.springframework.cloud.appbroker.pipeline.output.TransformedParameters;
import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;

public class CloudFoundryAppDeploymentStep<T, S extends Serializable, A extends Serializable> implements
	AppDeploymentStep<
		TransformedParameters<?>,
		DeployedServices<?>,
		DeployedApp<?>> {

	@Override
	public Tuple4<ServiceBrokerRequest, TransformedParameters<?>, DeployedServices<?>, DeployedApp<?>>
	apply(Tuple3<ServiceBrokerRequest, TransformedParameters<?>, DeployedServices<?>> previousStep) {
		return Tuple.tuple(previousStep.v1, previousStep.v2, previousStep.v3, new CloudFoundryDeployedApp());
	}
}