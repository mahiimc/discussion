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

import com.discussion.handler.MapperHandler;
import com.discussion.handler.ResponseBuilder;
import com.discussion.model.Post;
import com.discussion.service.PostService;
import com.discussion.utils.JacksonUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	private final MapperHandler mapper;
	private final ResponseBuilder responseBuilder;

	@PostMapping("/")
	public ResponseEntity<ObjectNode> savePost(@RequestBody ObjectNode post) {
		Post postModel = mapper.map(post, Post.class);
		Post responsePost = postService.savePost(postModel);
		ObjectNode response = responseBuilder.build(responsePost);
		return new ResponseEntity<ObjectNode>(response, HttpStatus.CREATED);
	}

	@PutMapping("/")
	public ResponseEntity<ObjectNode> updatePost(@RequestBody ObjectNode post) {
		Post postModel = mapper.map(post, Post.class);
		Post responsePost = postService.savePost(postModel);
		ObjectNode response = responseBuilder.build(responsePost);
		return new ResponseEntity<ObjectNode>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<ObjectNode> deletePost(@PathVariable("postId") Long postId) {
		Long deletedPostId = postService.deletePost(postId);
		ObjectNode data = JacksonUtils.objectNode();
		data.put("postId", deletedPostId);
		data.put("message", "deleted post successfully.");
		return new ResponseEntity<ObjectNode>(responseBuilder.build(data), HttpStatus.ACCEPTED);
	}

	@GetMapping("/")
	public ResponseEntity<ObjectNode> findAll() {
		List<Post> posts = postService.getPosts();
		return new ResponseEntity<ObjectNode>(responseBuilder.build(posts), HttpStatus.OK);
	}

	@GetMapping("/{postId}")
	public ResponseEntity<ObjectNode> findById(@PathVariable("postId") Long postId) {
		Post post = postService.getPost(postId);
		return new ResponseEntity<ObjectNode>(responseBuilder.build(post), HttpStatus.OK);
	}
}
