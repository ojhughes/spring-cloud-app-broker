package org.springframework.cloud.appbroker.pipeline.output;

import java.util.HashMap;
import java.util.Map;

public class CloudFoundryDeployedServices implements DeployedServices {

	@Override
	public Map<String, String> serviceStates() {
		return new HashMap<>();
	}
}
