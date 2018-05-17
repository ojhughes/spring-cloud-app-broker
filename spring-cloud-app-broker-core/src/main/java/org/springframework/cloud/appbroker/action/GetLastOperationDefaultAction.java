package org.springframework.cloud.appbroker.action;

import org.springframework.cloud.servicebroker.model.instance.GetLastServiceOperationRequest;
import org.springframework.cloud.servicebroker.model.instance.GetLastServiceOperationResponse;

public class GetLastOperationDefaultAction implements GetLastOperationAction<GetLastServiceOperationRequest, GetLastServiceOperationResponse> {

	@Override
	public GetLastServiceOperationResponse perform(GetLastServiceOperationRequest requestData) {
		return null;
	}
}
