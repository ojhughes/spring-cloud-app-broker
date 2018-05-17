package org.springframework.cloud.appbroker.action;

import org.springframework.cloud.servicebroker.model.instance.GetServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.GetServiceInstanceResponse;

public class GetServiceInstanceDefaultAction implements GetServiceInstanceAction<GetServiceInstanceRequest, GetServiceInstanceResponse> {

	@Override
	public GetServiceInstanceResponse perform(GetServiceInstanceRequest requestData) {
		return null;
	}
}
