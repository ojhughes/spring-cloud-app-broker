package org.springframework.cloud.appbroker.pipeline.output;

public interface PersistResponse<T extends Comparable<T>> {
	T identifier();
}
