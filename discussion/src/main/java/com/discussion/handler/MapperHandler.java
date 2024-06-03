package com.discussion.handler;

import org.springframework.stereotype.Component;

import com.discussion.dto.CommentDTO;
import com.discussion.model.Comment;
import com.discussion.model.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MapperHandler {

	private final ObjectMapper mapper;

	public <T> T map(ObjectNode object, Class<T> clazz) {
		return map(object.toString(), clazz);
	}

	public <T> T map(String obj, Class<T> clazz) {
		try {
			return mapper.readValue(obj, clazz);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public ObjectNode map(Object  object) {
		try {
			return mapper.readValue(object.toString(), ObjectNode.class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Comment map(CommentDTO dto) {
		Comment comment = new Comment();
		comment.setContent(dto.getContent());
		Post post = new Post();
		post.setId(dto.getPostId());
		comment.setPost(post);
		comment.setId(dto.getId());
		return comment;
	}
}
