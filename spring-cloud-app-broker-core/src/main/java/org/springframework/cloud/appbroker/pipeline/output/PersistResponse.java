package org.springframework.cloud.appbroker.pipeline.output;

public interface PersistResponse<T> {
	T identifier();
}
