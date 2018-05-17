package org.springframework.cloud.appbroker.pipeline.step;

import java.util.function.Function;

import org.jooq.lambda.function.Function1;
import org.jooq.lambda.function.Function2;
import org.jooq.lambda.function.Function3;
import org.jooq.lambda.tuple.Tuple3;
import org.jooq.lambda.tuple.Tuple4;

import org.springframework.cloud.appbroker.pipeline.output.DeployedApp;
import org.springframework.cloud.appbroker.pipeline.output.DeployedServices;
import org.springframework.cloud.appbroker.pipeline.output.TransformedParameters;
import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;
import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceRequest;


public interface AppDeploymentStep<
	T extends TransformedParameters,
	S extends DeployedServices,
	A extends DeployedApp>

	extends Function<
		Tuple3<ServiceBrokerRequest, T, S>,
		Tuple4<ServiceBrokerRequest, T, S, A>> {
}
