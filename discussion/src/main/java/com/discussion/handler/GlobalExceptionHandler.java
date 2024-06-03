package com.discussion.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.discussion.exception.DiscussionException;
import com.discussion.utils.JacksonUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(DiscussionException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ObjectNode handleDiscussionException(DiscussionException exception) {
		ObjectNode error = JacksonUtils.objectNode();
		error.put("message", exception.getMessage());
		return error;
	}

}
