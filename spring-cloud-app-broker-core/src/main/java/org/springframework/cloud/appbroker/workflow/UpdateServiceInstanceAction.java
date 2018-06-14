package org.springframework.cloud.appbroker.workflow;

import org.springframework.cloud.servicebroker.model.instance.UpdateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.UpdateServiceInstanceResponse;

public interface UpdateServiceInstanceAction<REQ extends UpdateServiceInstanceRequest, RES extends UpdateServiceInstanceResponse>
	extends AppBrokerAction<REQ, RES> {
}
