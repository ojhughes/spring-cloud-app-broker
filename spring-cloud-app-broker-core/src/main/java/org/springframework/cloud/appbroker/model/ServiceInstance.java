package org.springframework.cloud.appbroker.model;

import java.util.Map;

public class ServiceInstance {

	private final String instanceId;
	private final Map<String, Object> parameters;
	private final String planId;
	private final String serviceDefinitionId;

	public ServiceInstance(String instanceId, String serviceDefinitionId, String planId,
						   Map<String, Object> parameters) {
		this.instanceId = instanceId;
		this.serviceDefinitionId = serviceDefinitionId;
		this.planId = planId;
		this.parameters = parameters;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public String getPlanId() {
		return planId;
	}

	public String getServiceDefinitionId() {
		return serviceDefinitionId;
	}
}