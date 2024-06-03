package com.discussion.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AuthenticationManagerImpl implements AuthenticationManager {
	
	
	private final AuthenticationProvider authProvider;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return authProvider.authenticate(authentication);
	}

}
