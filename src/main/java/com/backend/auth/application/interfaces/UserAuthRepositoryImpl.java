package com.backend.auth.application.interfaces;

import com.backend.auth.domain.UserAuth;
import com.backend.auth.repository.entity.UserAuthEntity;
import com.backend.auth.repository.jpa.JpaUserAuthRepository;
import com.backend.user.application.interfaces.UserRepository;
import com.backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class UserAuthRepositoryImpl implements UserAuthRepository {

    private final JpaUserAuthRepository jpaUserAuthRepository;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserAuth registerUser(UserAuth userAuth, User user) {
        User savedUser = userRepository.save(user);
        UserAuthEntity userAuthEntity = jpaUserAuthRepository.save(
            UserAuthEntity.createUserAuthEntity(userAuth, savedUser.getId()));
        return userAuthEntity.toUserAuth();
    }
}
