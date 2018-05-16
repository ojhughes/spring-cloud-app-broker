package org.springframework.cloud.appbroker.lifecycle;

import org.springframework.cloud.servicebroker.model.instance.GetServiceInstanceRequest;

public class GetServiceInMemoryPersistenceAction implements GetServicePersistenceAction<GetServiceInstanceRequest, ActionResponse> {

	@Override
	public ActionResponse perform(GetServiceInstanceRequest requestData) {
		return null;
	}
}
