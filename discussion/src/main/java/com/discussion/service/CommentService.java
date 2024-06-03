package com.discussion.service;
import java.util.List;

import com.discussion.model.Comment;

public interface CommentService {
    Comment saveComment(Comment comment);
    Long deleteComment(Comment comment);
    Long deleteComment(Long commentId);
    Comment getComment(Long commentId);
    List<Comment> getCommentsByPostId(Long postId);
}
