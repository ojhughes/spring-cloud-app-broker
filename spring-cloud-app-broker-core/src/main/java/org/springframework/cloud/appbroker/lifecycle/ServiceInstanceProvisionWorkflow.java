package org.springframework.cloud.appbroker.lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
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

	private CreateServicePlatformAction createServicePlatformAction;
	private CreateServicePersistenceAction createServicePersistenceAction;
	private CreateServiceResponseBuilderAction createServiceResponseBuilderAction;
	private CreateServiceSecurityAction createServiceSecurityAction;
	private UpdateServicePlatformAction updateServicePlatformAction;
	private UpdateServicePersistenceAction updateServicePersistenceAction;
	private GetServicePlatformAction getServicePlatformAction;
	private GetServicePersistenceAction getServicePersistenceAction;
	private GetLastOperationPlatformAction getLastOperationPlatformAction;
	private GetLastOperationPersistenceAction getLastOperationPersistenceAction;
	private DeleteServicePlatformAction deleteServicePlatformAction;
	private DeleteServicePersistenceAction deleteServicePersistenceAction;
	private DeleteServiceSecurityAction deleteServiceSecurityAction;

	@Autowired
	public ServiceInstanceProvisionWorkflow(CreateServicePlatformAction createServicePlatformAction,
											CreateServicePersistenceAction createServicePersistenceAction,
											CreateServiceResponseBuilderAction createServiceResponseBuilderAction,
											CreateServiceSecurityAction createServiceSecurityAction,
											UpdateServicePlatformAction updateServicePlatformAction,
											UpdateServicePersistenceAction updateServicePersistenceAction,
											GetServicePlatformAction getServicePlatformAction,
											GetServicePersistenceAction getServicePersistenceAction,
											GetLastOperationPlatformAction getLastOperationPlatformAction,
											GetLastOperationPersistenceAction getLastOperationPersistenceAction,
											DeleteServicePlatformAction deleteServicePlatformAction,
											DeleteServicePersistenceAction deleteServicePersistenceAction,
											DeleteServiceSecurityAction deleteServiceSecurityAction) {

		this.createServicePlatformAction = createServicePlatformAction;
		this.createServicePersistenceAction = createServicePersistenceAction;
		this.createServiceResponseBuilderAction = createServiceResponseBuilderAction;
		this.createServiceSecurityAction = createServiceSecurityAction;
		this.updateServicePlatformAction = updateServicePlatformAction;
		this.updateServicePersistenceAction = updateServicePersistenceAction;
		this.getServicePlatformAction = getServicePlatformAction;
		this.getServicePersistenceAction = getServicePersistenceAction;
		this.getLastOperationPlatformAction = getLastOperationPlatformAction;
		this.getLastOperationPersistenceAction = getLastOperationPersistenceAction;
		this.deleteServicePlatformAction = deleteServicePlatformAction;
		this.deleteServicePersistenceAction = deleteServicePersistenceAction;
		this.deleteServiceSecurityAction = deleteServiceSecurityAction;
	}

	@Override
	@SuppressWarnings("unchecked")
	public CreateServiceInstanceResponse createServiceInstance(CreateServiceInstanceRequest request) {
		ActionResponse platformActionResponse = createServicePlatformAction.perform(request);
		ActionResponse securityActionResponse = createServiceSecurityAction.perform(request);
		ActionResponse persistenceActionResponse = createServicePersistenceAction.perform(request);
		return createServiceResponseBuilderAction.build(request, platformActionResponse, securityActionResponse, persistenceActionResponse);
	}

	@Override
	public GetServiceInstanceResponse getServiceInstance(GetServiceInstanceRequest request) {
		return null;
	}

	@Override
	public GetLastServiceOperationResponse getLastOperation(GetLastServiceOperationRequest request) {
		return null;
	}

	@Override
	public DeleteServiceInstanceResponse deleteServiceInstance(DeleteServiceInstanceRequest request) {
		return null;
	}

	@Override
	public UpdateServiceInstanceResponse updateServiceInstance(UpdateServiceInstanceRequest request) {
		return null;
	}
}
