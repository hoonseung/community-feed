package com.backend.post.application.interfaces;

import com.backend.post.domain.Post;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    Optional<Post> findById(Long id);
}
