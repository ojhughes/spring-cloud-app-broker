package org.springframework.cloud.appbroker.pipeline.output;

public interface DeployedApp<T> {
	T getState();
}
