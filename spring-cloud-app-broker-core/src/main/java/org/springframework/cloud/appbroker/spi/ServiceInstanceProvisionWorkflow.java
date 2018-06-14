package org.springframework.cloud.appbroker.spi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.appbroker.workflow.CreateServiceInstanceAction;
import org.springframework.cloud.appbroker.workflow.DeleteServiceInstanceAction;
import org.springframework.cloud.appbroker.workflow.GetLastOperationAction;
import org.springframework.cloud.appbroker.workflow.GetServiceInstanceAction;
import org.springframework.cloud.appbroker.workflow.UpdateServiceInstanceAction;
import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.CreateServiceInstanceResponse;
import org.springframework.cloud.servicebroker.model.instance.DeleteServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.DeleteServiceInstanceResponse;
import org.springframework.cloud.servicebroker.model.instance.GetLastServiceOperationRequest;
import org.springframework.cloud.servicebroker.model.instance.GetLastServiceOperationResponse;
import org.springframework.cloud.servicebroker.model.instance.GetServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.GetServiceInstanceResponse;
import org.springframework.cloud.servicebroker.model.instance.UpdateServiceInstanceRequest;
import org.springframework.cloud.servicebroker.model.instance.UpdateServiceInstanceResponse;
import org.springframework.cloud.servicebroker.service.ServiceInstanceService;
import org.springframework.stereotype.Component;

@Component
public class ServiceInstanceProvisionWorkflow implements ServiceInstanceService {

	private CreateServiceInstanceAction<CreateServiceInstanceRequest, CreateServiceInstanceResponse> createServiceInstanceAction;
	private UpdateServiceInstanceAction<UpdateServiceInstanceRequest, UpdateServiceInstanceResponse>updateServiceInstanceAction;
	private GetServiceInstanceAction<GetServiceInstanceRequest, GetServiceInstanceResponse> getServiceInstanceAction;
	private GetLastOperationAction<GetLastServiceOperationRequest, GetLastServiceOperationResponse> getLastOperationAction;
	private DeleteServiceInstanceAction<DeleteServiceInstanceRequest, DeleteServiceInstanceResponse> deleteServiceInstanceAction;


	@Autowired
	public ServiceInstanceProvisionWorkflow(CreateServiceInstanceAction<CreateServiceInstanceRequest, CreateServiceInstanceResponse> createServiceInstanceAction,
											UpdateServiceInstanceAction<UpdateServiceInstanceRequest, UpdateServiceInstanceResponse> updateServiceInstanceAction,
											GetServiceInstanceAction<GetServiceInstanceRequest, GetServiceInstanceResponse> getServiceInstanceAction,
											GetLastOperationAction<GetLastServiceOperationRequest, GetLastServiceOperationResponse> getLastOperationAction,
											DeleteServiceInstanceAction<DeleteServiceInstanceRequest, DeleteServiceInstanceResponse> deleteServiceInstanceAction) {

		this.createServiceInstanceAction = createServiceInstanceAction;
		this.updateServiceInstanceAction = updateServiceInstanceAction;
		this.getServiceInstanceAction = getServiceInstanceAction;
		this.getLastOperationAction = getLastOperationAction;
		this.deleteServiceInstanceAction = deleteServiceInstanceAction;
	}

	@Override
	public CreateServiceInstanceResponse createServiceInstance(CreateServiceInstanceRequest request) {
		return createServiceInstanceAction.perform(request);
	}

	@Override
	public GetServiceInstanceResponse getServiceInstance(GetServiceInstanceRequest request) {
		return getServiceInstanceAction.perform(request);
	}

	@Override
	public GetLastServiceOperationResponse getLastOperation(GetLastServiceOperationRequest request) {
		return getLastOperationAction.perform(request);
	}

	@Override
	public DeleteServiceInstanceResponse deleteServiceInstance(DeleteServiceInstanceRequest request) {
		return deleteServiceInstanceAction.perform(request);
	}

	@Override
	public UpdateServiceInstanceResponse updateServiceInstance(UpdateServiceInstanceRequest request) {
		return updateServiceInstanceAction.perform(request);
	}
}
