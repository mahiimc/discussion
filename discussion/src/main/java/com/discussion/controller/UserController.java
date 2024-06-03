package com.discussion.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.discussion.handler.MapperHandler;
import com.discussion.handler.ResponseBuilder;
import com.discussion.handler.TokenHandler;
import com.discussion.model.User;
import com.discussion.service.UserService;
import com.discussion.utils.JacksonUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
	
	private final MapperHandler mapperHandler;
	private final UserService userService;
	private final ResponseBuilder responseBuilder;
	private final AuthenticationManager authManager;
	
	@PostMapping("/register")
	public ResponseEntity<ObjectNode> registerUser(@RequestBody ObjectNode node) {
		User user = mapperHandler.map(node, User.class);
		userService.saveUser(user);
		user.setPassword(null);
		return new ResponseEntity<>(responseBuilder.build(user),HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ObjectNode> loginUser(@RequestBody ObjectNode node) {
		final String username = node.get("username").asText();
		final String password = node.get("password").asText();
		
		Authentication authentication =  authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = TokenHandler.generate(authentication);
		ObjectNode response = JacksonUtils.objectNode();
		response.put("token", token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
