package org.springframework.cloud.appbroker.pipeline.step;

import java.util.function.Function;

import org.springframework.cloud.appbroker.pipeline.output.GeneratedCredentials;

public interface CredentialGenerator<
	G extends GeneratedCredentials<?>>

	extends Function<G>>{
}
