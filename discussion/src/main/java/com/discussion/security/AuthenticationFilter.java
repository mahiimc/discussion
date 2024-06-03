package com.discussion.security;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.discussion.constants.Constants;
import com.discussion.handler.TokenHandler;
import com.discussion.model.SecuredUser;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Component
public class AuthenticationFilter extends OncePerRequestFilter {
	
	private final UserDetailsService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = parse(request);
		if (StringUtils.isNotBlank(token)) {
			String userName = TokenHandler.extract(token);
			SecuredUser userDetails = (SecuredUser) userService.loadUserByUsername(userName);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private final String parse(HttpServletRequest request) {
		String authHeader = request.getHeader(Constants.AUTH_HEADER);
		if(StringUtils.isNotBlank(authHeader) && authHeader.startsWith(Constants.BEARER)) {
			return authHeader.substring(7);
		}
		log.warn("no Authorization Header found for url : {}", request.getRequestURI());
		return "";
		
		
	}
	
	
}
