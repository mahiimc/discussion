package com.discussion.exception;

public class DiscussionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DiscussionException(String message) {
		super(message);
	}

	public DiscussionException(Throwable throwable) {
		super(throwable);
	}

}
