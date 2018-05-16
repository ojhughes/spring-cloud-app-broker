package org.springframework.cloud.appbroker.lifecycle;

import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;

public interface AppBrokerAction<REQ extends ServiceBrokerRequest, RES extends ActionResponse> {
	RES perform(REQ requestData);
}
