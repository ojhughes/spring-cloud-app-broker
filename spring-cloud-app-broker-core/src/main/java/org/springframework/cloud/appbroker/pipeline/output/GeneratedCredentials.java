package org.springframework.cloud.appbroker.pipeline.output;


import java.io.Serializable;

public interface GeneratedCredentials<T extends Serializable> {
	T getAuthentication();
}
