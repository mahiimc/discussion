package com.discussion.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.discussion.exception.DiscussionException;
import com.discussion.model.Comment;
import com.discussion.repository.CommentRepository;
import com.discussion.service.CommentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
	
	private final CommentRepository repository;

	@Override
	public Comment saveComment(Comment comment) {
		return repository.save(comment);
	}

	@Override
	public Long deleteComment(Comment comment) {
		 repository.delete(comment);
		 return comment.getId();
	}

	@Override
	public Long deleteComment(Long commentId) {
		Comment dbComment = getComment(commentId);
		if ( dbComment == null ) {
			throw new DiscussionException("Comment does not exists.");
		}
		return deleteComment(dbComment);
	}

	@Override
	public Comment getComment(Long commentId) {
		return repository.findById(commentId).orElse(null);
	}


	@Transactional
	@Override
	public List<Comment> getCommentsByPostId(Long postId) {
		return repository.findAllCommentsByPostId(postId);
	}

}
