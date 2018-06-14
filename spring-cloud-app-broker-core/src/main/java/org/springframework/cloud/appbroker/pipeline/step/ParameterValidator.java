package org.springframework.cloud.appbroker.pipeline.step;

import java.util.function.Function;

import org.jooq.lambda.tuple.Tuple2;

import org.springframework.cloud.appbroker.pipeline.output.ValidationResult;
import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;

public interface ParameterValidator extends Function<
	ServiceBrokerRequest,
	Tuple2<ServiceBrokerRequest, ValidationResult>> {
}
