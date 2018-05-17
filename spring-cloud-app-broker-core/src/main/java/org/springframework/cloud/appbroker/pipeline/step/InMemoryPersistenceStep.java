package org.springframework.cloud.appbroker.pipeline.step;

import java.util.concurrent.ConcurrentHashMap;

import org.jooq.lambda.tuple.Tuple;
import org.jooq.lambda.tuple.Tuple5;
import org.jooq.lambda.tuple.Tuple6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.appbroker.model.ServiceInstance;
import org.springframework.cloud.appbroker.pipeline.output.DeployedApp;
import org.springframework.cloud.appbroker.pipeline.output.DeployedServices;
import org.springframework.cloud.appbroker.pipeline.output.GeneratedCredentials;
import org.springframework.cloud.appbroker.pipeline.output.InMemoryPersistResponse;
import org.springframework.cloud.appbroker.pipeline.output.PersistResponse;
import org.springframework.cloud.appbroker.pipeline.output.TransformedParameters;
import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;
import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceRequest;

public class InMemoryPersistenceStep implements
	PersistenceStep<
		TransformedParameters,
		DeployedServices,
		DeployedApp,
		GeneratedCredentials,
		PersistResponse> {

	private ConcurrentHashMap<String, ServiceInstance> inMemoryMap;

	@Autowired
	public InMemoryPersistenceStep(ConcurrentHashMap<String, ServiceInstance> inMemoryMap) {
		this.inMemoryMap = inMemoryMap;
	}

	@Override
	public Tuple6<ServiceBrokerRequest, TransformedParameters, DeployedServices, DeployedApp, GeneratedCredentials, PersistResponse>
	apply(Tuple5<ServiceBrokerRequest, TransformedParameters, DeployedServices, DeployedApp, GeneratedCredentials> previousStep) {
		CreateServiceInstanceRequest serviceInstanceRequest = (CreateServiceInstanceRequest) previousStep.v1;
		ServiceInstance serviceToPersist = new ServiceInstance(
			serviceInstanceRequest.getServiceInstanceId(),
			serviceInstanceRequest.getServiceDefinitionId(),
			serviceInstanceRequest.getPlanId(),
			serviceInstanceRequest.getParameters());
		inMemoryMap.putIfAbsent(previousStep.v1.getPlatformInstanceId(), serviceToPersist);
		return Tuple.tuple(previousStep.v1, previousStep.v2, previousStep.v3, previousStep.v4, previousStep.v5, new InMemoryPersistResponse(serviceInstanceRequest.getServiceDefinitionId()));
	}
}
