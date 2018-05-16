package org.springframework.cloud.appbroker.lifecycle;

import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceRequest;

public class CreateServiceCloudPlatformFoundryPlatformAction implements CreateServicePlatformAction<CreateServiceInstanceRequest, ActionResponse> {

	@Override
	public ActionResponse perform(CreateServiceInstanceRequest requestData) {
		return new ActionResponse() {
			@Override
			boolean success() {
				return false;
			}
		};
	}
}
