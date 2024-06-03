package com.discussion.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.discussion.handler.MapperHandler;
import com.discussion.model.Post;
import com.discussion.service.RedisService;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Component
@Aspect
@Slf4j
public class CacheAspect {

	private final RedisService redisService;
	private final MapperHandler mapperHandler;

	@Around("execute(public com.discussion.service.impl.PostServiceImpl.savePost(..))")
	public void addCache(ProceedingJoinPoint joinPoint) {
		try {
			Object object = joinPoint.proceed();
			if (object instanceof Post post) {
//				redisService.setValue("",post);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	
	@Pointcut("execution(* com.discussion.controller.PostController.*(..))")
    public void allPostServiceMethods() {}
	
	
	public ResponseEntity<ObjectNode> getCache(ProceedingJoinPoint joinPoint) {
		try {
			Object[] args = joinPoint.getArgs();
			if ( args != null ) {
				String postId = String.valueOf(args[0]);
				ResponseEntity<ObjectNode> post = redisService.getValue(postId);
				if ( post == null ) {
					log.info("Cache missed .. asking db for data.");
					post =  (ResponseEntity<ObjectNode>) joinPoint.proceed();
					redisService.setValue(postId, post);
					return post;
				}
				log.info("Found the cache {}",post);
				return post;
			}
		} catch (Throwable e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

}
