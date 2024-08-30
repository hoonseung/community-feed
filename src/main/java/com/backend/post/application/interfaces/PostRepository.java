package com.backend.post.application.interfaces;

import com.backend.post.domain.Post;
import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    Post findById(Long id);
}
