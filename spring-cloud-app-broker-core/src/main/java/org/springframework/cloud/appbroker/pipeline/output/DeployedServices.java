package org.springframework.cloud.appbroker.pipeline.output;

public interface DeployedServices<T> {

	T serviceStates();
}
