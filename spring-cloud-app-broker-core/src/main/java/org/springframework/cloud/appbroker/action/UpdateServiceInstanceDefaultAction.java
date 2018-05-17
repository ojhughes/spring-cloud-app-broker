package org.springframework.cloud.appbroker.action;

import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;
import org.springframework.cloud.servicebroker.model.instance.UpdateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.UpdateServiceInstanceResponse;

public class UpdateServiceInstanceDefaultAction implements UpdateServiceInstanceAction<UpdateServiceInstanceRequest, UpdateServiceInstanceResponse> {

	@Override
	public UpdateServiceInstanceResponse perform(UpdateServiceInstanceRequest requestData) {
		return null;
	}
}
