package com.backend.user.repository;

import com.backend.user.application.interfaces.UserRepository;
import com.backend.user.domain.User;
import com.backend.user.repository.entity.UserEntity;
import com.backend.user.repository.jpa.JpaUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Transactional
    @Override
    public User save(User user) {
        UserEntity userEntity = jpaUserRepository.save(UserEntity.createUserEntity(user));
        return userEntity.toUser();
    }

    @Override
    public User findById(Long id) {
        return jpaUserRepository.findById(id)
            .map(UserEntity::toUser)
            .orElseThrow(() -> new EntityNotFoundException("user entity not found"));
    }
}
