package org.springframework.cloud.appbroker.workflow;

import org.springframework.cloud.servicebroker.model.instance.GetLastServiceOperationRequest;
import org.springframework.cloud.servicebroker.model.instance.GetLastServiceOperationResponse;

public interface GetLastOperationAction<REQ extends GetLastServiceOperationRequest, RES extends GetLastServiceOperationResponse>
	extends AppBrokerAction<REQ, RES> {
}
