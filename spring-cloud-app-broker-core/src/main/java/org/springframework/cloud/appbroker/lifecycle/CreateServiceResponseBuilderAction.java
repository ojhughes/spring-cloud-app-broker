package org.springframework.cloud.appbroker.lifecycle;

import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceResponse;

public class CreateServiceResponseBuilderAction {

	public CreateServiceInstanceResponse build(CreateServiceInstanceRequest request, ActionResponse platformActionResponse, ActionResponse securityActionResponse, ActionResponse persistenceActionResponse) {
		return null;
	}
}
