package com.backend.user.repository;

import com.backend.user.application.interfaces.UserRepository;
import com.backend.user.domain.User;
import com.backend.user.repository.entity.UserEntity;
import com.backend.user.repository.jpa.JpaUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity userEntity = jpaUserRepository.save(UserEntity.createUserEntity(user));
        return userEntity.toUser();
    }

    @Override
    public User findById(Long id) {
        UserEntity userEntity = jpaUserRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("user entity not found"));

        return userEntity.toUser();
    }
}
