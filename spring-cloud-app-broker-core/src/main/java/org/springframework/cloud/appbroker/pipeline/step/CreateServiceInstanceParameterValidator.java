package org.springframework.cloud.appbroker.pipeline.step;

import java.util.function.Function;

import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;

import org.springframework.cloud.appbroker.pipeline.output.ValidationResult;
import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;

public class CreateServiceInstanceParameterValidator implements ParameterValidatorStep {

	@Override
	public Tuple2<ServiceBrokerRequest, ValidationResult> apply(ServiceBrokerRequest serviceBrokerRequest) {
		return Tuple.tuple(serviceBrokerRequest, ValidationResult.valid());
	}
}
