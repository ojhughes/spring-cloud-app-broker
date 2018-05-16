package org.springframework.cloud.appbroker.lifecycle;

import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;
import org.springframework.cloud.servicebroker.model.instance.GetServiceInstanceRequest;

public class GetServiceCloudFoundryPlatformAction implements GetServicePlatformAction<GetServiceInstanceRequest, ActionResponse> {

	@Override
	public ActionResponse perform(GetServiceInstanceRequest requestData) {
		return null;
	}
}
