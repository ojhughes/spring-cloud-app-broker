package org.springframework.cloud.appbroker.pipeline.step;

import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple2;

import org.springframework.cloud.appbroker.pipeline.output.NoOpTransformedParameters;
import org.springframework.cloud.appbroker.pipeline.output.TransformedParameters;
import org.springframework.cloud.appbroker.pipeline.output.ValidationResult;
import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;

public class CreateServiceInstanceParameterMapper<T> implements ParameterTransformer<TransformedParameters<?>> {

	@Override
	public Tuple2<ServiceBrokerRequest, TransformedParameters<?>> apply(Tuple2<ServiceBrokerRequest, ValidationResult> previousStep) {

		return Tuple.tuple(previousStep.v1, new NoOpTransformedParameters());
	}
}
