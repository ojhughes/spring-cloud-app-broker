package org.springframework.cloud.appbroker.pipeline.output;

import java.util.HashMap;
import java.util.Map;

public class CloudFoundryDeployedApp implements DeployedApp {

	@Override
	public Map<String, String> getState() {
		return new HashMap<>();
	}
}
