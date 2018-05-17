package org.springframework.cloud.appbroker.pipeline.output;

import java.util.Map;

public interface TransformedParameters<T> {
	T transform(Map<String, Object> inputParameters);
}
