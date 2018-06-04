package org.springframework.cloud.appbroker.pipeline.output;

public class InMemoryPersistResponse<R extends S> implements PersistResponse<R> {

	private String identifier;

	public InMemoryPersistResponse(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public String identifier() {
		return identifier;
	}
}
