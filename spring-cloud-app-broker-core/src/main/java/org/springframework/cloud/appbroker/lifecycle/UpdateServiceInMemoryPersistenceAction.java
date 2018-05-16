package org.springframework.cloud.appbroker.lifecycle;

import org.springframework.cloud.servicebroker.model.instance.UpdateServiceInstanceRequest;

public class UpdateServiceInMemoryPersistenceAction implements UpdateServicePersistenceAction<UpdateServiceInstanceRequest, ActionResponse> {

	@Override
	public ActionResponse perform(UpdateServiceInstanceRequest requestData) {
		return null;
	}
}
