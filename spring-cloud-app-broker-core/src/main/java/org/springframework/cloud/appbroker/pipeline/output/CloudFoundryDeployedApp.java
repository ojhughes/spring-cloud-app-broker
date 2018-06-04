package org.springframework.cloud.appbroker.pipeline.output;

public class CloudFoundryDeployedApp implements DeployedApp<String> {

	@Override
	public String getState() {
		return "";
	}
}
