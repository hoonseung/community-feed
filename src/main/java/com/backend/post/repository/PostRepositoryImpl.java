package com.backend.post.repository;

import com.backend.post.application.interfaces.PostRepository;
import com.backend.post.domain.Post;
import com.backend.post.repository.entity.post.PostEntity;
import com.backend.post.repository.jpa.JpaPostRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;

    @Transactional
    @Override
    public Post save(Post post) {
        PostEntity postEntity = PostEntity.createPostEntity(post);
        if (Objects.isNull(post.getId())) {
            return jpaPostRepository.save(postEntity).toPost();
        }
        jpaPostRepository.updatePostEntity(postEntity);
        return postEntity.toPost();
    }

    @Override
    public Post findById(Long id) {
        return jpaPostRepository.findById(id)
            .map(PostEntity::toPost)
            .orElseThrow(() -> new EntityNotFoundException("post not found"));

    }
}
