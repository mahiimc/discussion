package com.discussion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.discussion.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
	
	List<Comment> findAllCommentsByPostId(Long postId);
}
