package org.springframework.cloud.appbroker.pipeline.output;

import java.util.Optional;


public interface ValidationResult {

	static ValidationResult valid() {
		return new ValidationResult() {
			@Override
			public boolean isValid() {
				return true;
			}

			@Override
			public Optional<String> getReason() {
				return Optional.empty();
			}
		};
	}

	static ValidationResult invalid(String reason) {
		return new ValidationResult() {
			@Override
			public boolean isValid() {
				return false;
			}

			@Override
			public Optional<String> getReason() {
				return Optional.of(reason);
			}
		};
	}

	boolean isValid();

	Optional<String> getReason();
}


