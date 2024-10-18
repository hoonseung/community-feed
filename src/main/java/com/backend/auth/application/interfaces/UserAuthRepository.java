package com.backend.auth.application.interfaces;

import com.backend.auth.domain.UserAuth;
import com.backend.user.domain.User;

public interface UserAuthRepository {

    UserAuth registerUser(UserAuth userAuth, User user);
}
