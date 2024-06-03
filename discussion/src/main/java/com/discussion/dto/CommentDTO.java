package com.discussion.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CommentDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String content;
	private Long postId;
}
