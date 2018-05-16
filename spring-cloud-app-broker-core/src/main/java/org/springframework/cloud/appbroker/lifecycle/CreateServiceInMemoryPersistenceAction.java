package org.springframework.cloud.appbroker.lifecycle;

import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceRequest;

public class CreateServiceInMemoryPersistenceAction implements CreateServicePersistenceAction<CreateServiceInstanceRequest, ActionResponse> {

	@Override
	public ActionResponse perform(CreateServiceInstanceRequest requestData) {
		return null;
	}
}
