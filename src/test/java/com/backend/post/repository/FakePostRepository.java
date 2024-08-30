package com.backend.post.repository;

import com.backend.post.application.interfaces.PostRepository;
import com.backend.post.domain.Post;
import com.backend.post.domain.cotent.PostPublicationState;
import java.util.HashMap;
import java.util.Map;

public class FakePostRepository implements PostRepository {

    private final Map<Long, Post> store = new HashMap<>();
    private static Long id = 1L;

    @Override
    public Post save(Post post) {
        if (post.getId() != null) {
            store.put(post.getId(), post);
            return post;
        }

        Long postId = id++;
        Post newPost = new Post(postId, post.getAuthor(), post.getContent(), 0,
            PostPublicationState.PUBLIC);
        store.put(postId, newPost);
        return newPost;
    }

    @Override
    public Post findById(Long id) {
        return store.get(id);
    }
}
