package org.springframework.cloud.appbroker.pipeline.output;

public class InMemoryPersistResponse implements PersistResponse {

	private String identifier;

	public InMemoryPersistResponse(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public String identifier() {
		return identifier;
	}
}
