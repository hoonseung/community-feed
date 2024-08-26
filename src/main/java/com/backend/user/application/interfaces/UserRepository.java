package com.backend.user.application.interfaces;

import com.backend.user.domain.User;
import java.util.Optional;

public interface  UserRepository {

    User save(User user);
    Optional<User> findById(Long id);

}
