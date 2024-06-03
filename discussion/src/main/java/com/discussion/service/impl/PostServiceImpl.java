package com.discussion.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.discussion.exception.DiscussionException;
import com.discussion.model.Post;
import com.discussion.repository.PostRepository;
import com.discussion.service.PostService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository repository;


    @Override
    public Post savePost(Post post) {
        return repository.save(post);
    }

    @Override
    public Long deletePost(Post post) {
        repository.delete(post);
        return post.getId();
    }

    @Override
    public Long deletePost(Long postId) {
        Post dbPost = getPost(postId);
        if ( dbPost == null ) {
        	throw new DiscussionException("Post does not exists.");
        }
        return deletePost(dbPost);
    }
    
    @Transactional
    @Override
    public Post getPost(Long postId) {
        return repository.findById(postId).orElse(null);
    }

    @Override
    public List<Post> getPosts() {
        return repository.findAll();
    }
}
