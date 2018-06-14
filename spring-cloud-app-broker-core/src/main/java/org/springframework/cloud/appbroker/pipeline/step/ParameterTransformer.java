package org.springframework.cloud.appbroker.pipeline.step;

import java.util.function.Function;

import org.jooq.lambda.tuple.Tuple2;

import org.springframework.cloud.appbroker.pipeline.output.TransformedParameters;
import org.springframework.cloud.appbroker.pipeline.output.ValidationResult;
import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;

public interface ParameterTransformer<
	T extends TransformedParameters<?>>

	extends Function<
		Tuple2<ServiceBrokerRequest, ValidationResult>,
		Tuple2<ServiceBrokerRequest, T>> {
}
