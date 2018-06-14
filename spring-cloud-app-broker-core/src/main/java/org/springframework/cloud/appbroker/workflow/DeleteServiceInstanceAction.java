package org.springframework.cloud.appbroker.workflow;

import org.springframework.cloud.servicebroker.model.instance.DeleteServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.DeleteServiceInstanceResponse;

public interface DeleteServiceInstanceAction<REQ extends DeleteServiceInstanceRequest, RES extends DeleteServiceInstanceResponse>
	extends AppBrokerAction<REQ, RES> {
}
