package org.springframework.cloud.appbroker.action;

import org.springframework.cloud.servicebroker.model.ServiceBrokerRequest;
import org.springframework.cloud.servicebroker.model.instance.DeleteServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.DeleteServiceInstanceResponse;
import org.springframework.cloud.servicebroker.model.instance.UpdateServiceInstanceResponse;

public interface DeleteServiceInstanceAction<REQ extends DeleteServiceInstanceRequest, RES extends DeleteServiceInstanceResponse>
	extends AppBrokerAction<REQ, RES> {
}
