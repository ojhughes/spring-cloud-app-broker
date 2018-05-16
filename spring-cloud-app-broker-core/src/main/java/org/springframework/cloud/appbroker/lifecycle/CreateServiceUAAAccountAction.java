package org.springframework.cloud.appbroker.lifecycle;

import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceRequest;

public class CreateServiceUAAAccountAction implements CreateServiceSecurityAction<CreateServiceInstanceRequest, ActionResponse> {

	@Override
	public ActionResponse perform(CreateServiceInstanceRequest requestData) {
		return null;
	}
}
