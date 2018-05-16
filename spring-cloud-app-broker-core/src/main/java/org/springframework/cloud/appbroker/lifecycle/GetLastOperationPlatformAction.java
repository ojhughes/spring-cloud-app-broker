package org.springframework.cloud.appbroker.lifecycle;

import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;

public interface GetLastOperationPlatformAction<REQ extends ServiceBrokerRequest, RES extends ActionResponse>
	extends AppBrokerAction<REQ, RES> {
}
