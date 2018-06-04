package org.springframework.cloud.appbroker.pipeline.output;

import java.io.Serializable;
import java.util.Map;

public interface DeployedServices<T extends Serializable> {

	Map<String, T> serviceStates();
}
