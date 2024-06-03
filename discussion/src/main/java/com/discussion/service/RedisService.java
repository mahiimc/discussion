package com.discussion.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RedisService {

	private final RedisTemplate<String,ResponseEntity<ObjectNode>> redisTemplate;

	public void setValue(String  key, ResponseEntity<ObjectNode> value) {
		redisTemplate.opsForValue().set(key, value);
	}
	public ResponseEntity<ObjectNode> getValue(String key) {
		return redisTemplate.opsForValue().get(key);
	}
}
