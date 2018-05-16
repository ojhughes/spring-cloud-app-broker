package org.springframework.cloud.appbroker.lifecycle;

import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;
import org.springframework.cloud.servicebroker.model.instance.UpdateServiceInstanceRequest;

public class UpdateServiceCloudFoundryPlatformAction implements UpdateServicePlatformAction<UpdateServiceInstanceRequest, ActionResponse> {

	@Override
	public ActionResponse perform(UpdateServiceInstanceRequest requestData) {
		return null;
	}
}
