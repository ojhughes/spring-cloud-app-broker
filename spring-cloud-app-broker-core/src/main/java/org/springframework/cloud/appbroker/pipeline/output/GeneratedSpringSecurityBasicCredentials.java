package org.springframework.cloud.appbroker.pipeline.output;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class GeneratedSpringSecurityBasicCredentials implements GeneratedCredentials {

	@Override
	public UserDetails getAuthentication() {
		return User.builder().build();
	}
}
