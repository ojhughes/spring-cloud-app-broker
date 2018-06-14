package org.springframework.cloud.appbroker.pipeline.step;

import java.util.function.BiConsumer;


public interface AppDeployer extends BiConsumer<DeploymentRequestConfig, ServiceBrokerRequestState>
}
