package com.discussion.service;
import com.discussion.model.Post;

import java.util.List;

public interface PostService {
    Post savePost(Post post);
    Long deletePost(Post post);
    Long deletePost(Long postId);
    Post getPost(Long postId);
    List<Post> getPosts();
}
