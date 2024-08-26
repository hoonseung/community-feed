package com.backend.user.domain;


import com.backend.common.domain.PositiveIntegerCounter;
import java.util.Objects;
import lombok.Getter;

public class User {

    @Getter
    private final Long id;
    @Getter
    private final UserInfo userInfo;
    private final PositiveIntegerCounter followingCounter;
    private final PositiveIntegerCounter followerCounter;


    private User(Long id, UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalStateException("userInfo must not be null");
        }
        this.id = id;
        this.userInfo = userInfo;
        this.followingCounter = new PositiveIntegerCounter();
        this.followerCounter = new PositiveIntegerCounter();
    }


    private void follow(User targetUser) {
        if (targetUser.equals(this)) {
            throw new IllegalArgumentException("Cannot follow self");
        }
        targetUser.followerCountIncrement();
        this.followingCounter.increment();
    }


    public void unfollow(User targetUser) {
        if (targetUser.equals(this)) {
            throw new IllegalArgumentException("Cannot unfollow self");
        }
        targetUser.followerCountDecrement();
        this.followingCounter.decrement();
    }

    private void followerCountIncrement() {
        followerCounter.increment();
    }

    private void followerCountDecrement() {
        followerCounter.decrement();
    }

    public int getFollowingCount() {
        return followingCounter.getCount();
    }

    public int getFollowerCount() {
        return followerCounter.getCount();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof User user)) {
            return false;
        }
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
