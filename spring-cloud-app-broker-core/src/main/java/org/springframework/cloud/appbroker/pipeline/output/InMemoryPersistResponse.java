package org.springframework.cloud.appbroker.pipeline.output;

public class InMemoryPersistResponse implements PersistResponse<String> {

	private String identifier;

	public InMemoryPersistResponse(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public String identifier() {
		return identifier;
	}
}
