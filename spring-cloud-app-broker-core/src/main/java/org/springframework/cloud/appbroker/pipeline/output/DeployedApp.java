package org.springframework.cloud.appbroker.pipeline.output;

import java.io.Serializable;

public interface DeployedApp<T extends Serializable> {
	T getState();
}
