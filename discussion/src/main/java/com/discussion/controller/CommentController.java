package com.discussion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.discussion.dto.CommentDTO;
import com.discussion.handler.MapperHandler;
import com.discussion.handler.ResponseBuilder;
import com.discussion.model.Comment;
import com.discussion.service.CommentService;
import com.discussion.utils.JacksonUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;
	private final MapperHandler mapper;
	private final ResponseBuilder responseBuilder;

	@PostMapping("/")
	public ResponseEntity<ObjectNode> saveComment(@RequestBody CommentDTO comment) {
		Comment commentModel = mapper.map(comment);
		Comment responseComment = commentService.saveComment(commentModel);
		ObjectNode response = responseBuilder.build(responseComment);
		return new ResponseEntity<ObjectNode>(response, HttpStatus.CREATED);
	}

	@PutMapping("/")
	public ResponseEntity<ObjectNode> updateComment(@RequestBody CommentDTO comment) {
		Comment commentModel = mapper.map(comment);
		Comment responseComment = commentService.saveComment(commentModel);
		ObjectNode response = responseBuilder.build(responseComment);
		return new ResponseEntity<ObjectNode>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<ObjectNode> deleteComment(@PathVariable("commentId") Long commentId) {
		Long deleteCommentId = commentService.deleteComment(commentId);
		ObjectNode data = JacksonUtils.objectNode();
		data.put("commentId", deleteCommentId);
		data.put("message", "deleted comment successfully.");
		return new ResponseEntity<ObjectNode>(responseBuilder.build(data), HttpStatus.ACCEPTED);
	}

	@GetMapping("/{postId}")
	public ResponseEntity<ObjectNode> findAll(@PathVariable("postId") Long postId) {
		List<Comment> comments = commentService.getCommentsByPostId(postId);
		return new ResponseEntity<ObjectNode>(responseBuilder.build(comments), HttpStatus.OK);
	}

}
