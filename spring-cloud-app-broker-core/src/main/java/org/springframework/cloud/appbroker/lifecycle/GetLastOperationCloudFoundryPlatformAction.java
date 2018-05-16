package org.springframework.cloud.appbroker.lifecycle;

import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;
import org.springframework.cloud.servicebroker.model.instance.GetLastServiceOperationRequest;

public class GetLastOperationCloudFoundryPlatformAction implements GetLastOperationPlatformAction<GetLastServiceOperationRequest, ActionResponse> {

	@Override
	public ActionResponse perform(GetLastServiceOperationRequest requestData) {
		return null;
	}
}
