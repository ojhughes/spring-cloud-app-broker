package org.springframework.cloud.appbroker.pipeline.output;

import java.util.HashMap;
import java.util.Map;

public class NoOpTransformedParameters implements TransformedParameters<Map<String, Object>> {

	@Override
	public Map<String, Object> transform(Map<String, Object> inputParameters) {
		return inputParameters;
	}
}
