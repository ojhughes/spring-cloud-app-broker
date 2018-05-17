package org.springframework.cloud.appbroker.action;

import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;

public interface AppBrokerAction<REQ extends ServiceBrokerRequest, RES> {

	RES perform(REQ requestData);
}

