package org.springframework.cloud.appbroker.action;

import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceResponse;

public interface CreateServiceInstanceAction<REQ extends CreateServiceInstanceRequest, RES extends CreateServiceInstanceResponse>
	extends AppBrokerAction<REQ, RES> {
}
