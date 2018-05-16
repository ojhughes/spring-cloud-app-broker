package org.springframework.cloud.appbroker.lifecycle;

import org.springframework.cloud.servicebroker.model.instance.DeleteServiceInstanceRequest;

public class DeleteServiceCloudFoundryPlatformAction implements DeleteServicePlatformAction<DeleteServiceInstanceRequest, ActionResponse> {

	@Override
	public ActionResponse perform(DeleteServiceInstanceRequest requestData) {
		return null;
	}
}
