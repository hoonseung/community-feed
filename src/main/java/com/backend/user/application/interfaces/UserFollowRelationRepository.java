package com.backend.user.application.interfaces;

import com.backend.user.domain.User;

public interface UserFollowRelationRepository {

    boolean isAlreadyFollow(User user, User targetUser);
    void save(User user, User targetUser);
    void delete(User user, User targetUser);

}
