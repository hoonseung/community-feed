package com.backend.post.repository.jpa;

import com.backend.post.repository.entity.post.UserPostQueueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserPostQueueRepository extends JpaRepository<UserPostQueueEntity, Long> {

    void deleteByUserIdAndAuthorId(Long userId, Long authorId);


}
