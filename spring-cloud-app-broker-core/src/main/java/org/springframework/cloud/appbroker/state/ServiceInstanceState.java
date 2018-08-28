/*
 * Copyright 2016-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.appbroker.state;

import org.springframework.cloud.servicebroker.model.instance.OperationState;

/**
 * Object for persisting the state of a service instance operation
 *
 * @author Roy Clarkson
 */
public class ServiceInstanceState {

	private OperationState operationState;

	private String description;

	//TODO: consider a time stamp property

	public OperationState getOperationState() {
		return operationState;
	}

	public String getDescription() {
		return description;
	}

	public ServiceInstanceState(OperationState operationState, String description) {
		this.operationState = operationState;
		this.description = description;
	}

}
