package com.discussion.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.discussion.exception.DiscussionException;
import com.discussion.model.SecuredUser;
import com.discussion.model.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AuthenticationProvideImpl implements AuthenticationProvider {

	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		final String username = authentication.getName();
		final String password = authentication.getCredentials().toString();

		SecuredUser securedUser = (SecuredUser) userDetailsService.loadUserByUsername(username);
		
		User user = securedUser.getUser();
		
		if (user == null) {
			throw new DiscussionException("Invalid credentials provided.");
		}

		if (passwordEncoder.matches(password, user.getPassword())) {
			return new UsernamePasswordAuthenticationToken(securedUser.getUsername(), user.getPassword(),
					securedUser.getAuthorities());
		} else {
			throw new DiscussionException("Invalid credentials provided.");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
