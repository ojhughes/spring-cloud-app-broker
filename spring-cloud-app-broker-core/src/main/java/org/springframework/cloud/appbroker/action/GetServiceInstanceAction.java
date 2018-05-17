package org.springframework.cloud.appbroker.action;

import org.springframework.cloud.servicebroker.model.instance.GetServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.GetServiceInstanceResponse;

public interface GetServiceInstanceAction<REQ extends GetServiceInstanceRequest, RES extends GetServiceInstanceResponse>
	extends AppBrokerAction<REQ, RES> {
}
