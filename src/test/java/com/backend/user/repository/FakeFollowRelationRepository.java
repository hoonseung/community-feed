package com.backend.user.repository;

import com.backend.user.application.interfaces.UserFollowRelationRepository;
import com.backend.user.domain.User;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class FakeFollowRelationRepository implements UserFollowRelationRepository {

    private final Set<FollowRelation> followRelations = new HashSet<>();


    @Override
    public boolean isAlreadyFollow(User user, User targetUser) {
        return followRelations.contains(new FollowRelation(user.getId(), targetUser.getId()));
    }

    @Override
    public void save(User user, User targetUser) {
        followRelations.add(new FollowRelation(user.getId(), targetUser.getId()));
    }

    @Override
    public void delete(User user, User targetUser) {
        followRelations.remove(new FollowRelation(user.getId(), targetUser.getId()));
    }

    record FollowRelation(
        Long userId,
        Long targetUserId
    ) {

    }

}
