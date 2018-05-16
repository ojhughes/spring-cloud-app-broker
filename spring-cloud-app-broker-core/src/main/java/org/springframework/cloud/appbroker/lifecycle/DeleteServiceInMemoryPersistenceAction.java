package org.springframework.cloud.appbroker.lifecycle;

import org.springframework.cloud.servicebroker.model.instance.DeleteServiceInstanceRequest;

public class DeleteServiceInMemoryPersistenceAction implements DeleteServicePersistenceAction<DeleteServiceInstanceRequest, ActionResponse> {

	@Override
	public ActionResponse perform(DeleteServiceInstanceRequest requestData) {
		return null;
	}
}
