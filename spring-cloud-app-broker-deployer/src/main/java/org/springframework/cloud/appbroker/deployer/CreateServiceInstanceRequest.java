/*
 * Copyright 2002-2018 the original author or authors.
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

package org.springframework.cloud.appbroker.deployer;

import java.util.HashMap;
import java.util.Map;

public class CreateServiceInstanceRequest {

	private final String serviceInstanceName;
	private final String name;
	private final String plan;
	private final Map<String, String> parameters;
	private final String target;

	CreateServiceInstanceRequest(String serviceInstanceName,
								 String name,
								 String plan,
								 Map<String, String> parameters,
								 String target) {
		this.serviceInstanceName = serviceInstanceName;
		this.name = name;
		this.plan = plan;
		this.parameters = parameters;
		this.target = target;
	}



	public static CreateServiceInstanceRequestBuilder builder() {
		return new CreateServiceInstanceRequestBuilder();
	}

	public String getServiceInstanceName() {
		return serviceInstanceName;
	}

	public String getName() {
		return name;
	}

	public String getPlan() {
		return plan;
	}

	public Map<String, String> getParameters() {
		return parameters;
	}

	public String getTarget() {
		return target;
	}

	public static class CreateServiceInstanceRequestBuilder {

		private String serviceInstanceName;
		private String name;
		private String plan;
		private final Map<String, String> parameters = new HashMap<>();
		private String target;

		CreateServiceInstanceRequestBuilder() {
		}

		public CreateServiceInstanceRequestBuilder serviceInstanceName(String serviceInstanceName) {
			this.serviceInstanceName = serviceInstanceName;
			return this;
		}

		public CreateServiceInstanceRequestBuilder name(String name) {
			this.name = name;
			return this;
		}

		public CreateServiceInstanceRequestBuilder plan(String plan) {
			this.plan = plan;
			return this;
		}

		public CreateServiceInstanceRequestBuilder parameters(String key, String value) {
			this.parameters.put(key, value);
			return this;
		}

		public CreateServiceInstanceRequestBuilder parameters(Map<String, String> parameters) {
			if (parameters == null) {
				return this;
			}
			this.parameters.putAll(parameters);
			return this;
		}

		public CreateServiceInstanceRequestBuilder target(String target) {
			this.target = target;
			return this;
		}

		public CreateServiceInstanceRequest build() {
			return new CreateServiceInstanceRequest(serviceInstanceName, name, plan, parameters, target);
		}
	}

}
