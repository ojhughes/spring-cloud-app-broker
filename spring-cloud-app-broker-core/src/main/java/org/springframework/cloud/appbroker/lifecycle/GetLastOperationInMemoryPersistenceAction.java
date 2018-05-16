package org.springframework.cloud.appbroker.lifecycle;

import org.springframework.cloud.servicebroker.model.instance.GetLastServiceOperationRequest;

public class GetLastOperationInMemoryPersistenceAction implements GetLastOperationPersistenceAction<GetLastServiceOperationRequest, ActionResponse> {

	@Override
	public ActionResponse perform(GetLastServiceOperationRequest requestData) {
		return null;
	}
}
