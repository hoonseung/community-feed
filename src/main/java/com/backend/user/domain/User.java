package com.backend.user.domain;


import com.backend.common.domain.PositiveIntegerCounter;
import java.util.Objects;
import lombok.Getter;
import org.springframework.util.StringUtils;

public class User {

    @Getter
    private final Long id;
    @Getter
    private final UserInfo userInfo;
    private final PositiveIntegerCounter followerCounter;
    private final PositiveIntegerCounter followingCounter;


    public static User createUser(Long id, String name, String imageUrl) {
        return new User(id, new UserInfo(name, imageUrl));
    }

    public static User createUser(Long id, String name, String imageUrl, Integer followerCount,
        Integer followingCount) {
        return new User(id, name, imageUrl, followerCount, followingCount);
    }

    public static User createUser(String name, String imageUrl) {
        return new User(null, new UserInfo(name, imageUrl));
    }


    public User(Long id, String name, String imageUrl, Integer followerCount,
        Integer followingCount) {
        if (!StringUtils.hasText(name) && !StringUtils.hasText(imageUrl)) {
            throw new IllegalArgumentException("userInfo must not be null");
        }
        this.id = id;
        this.userInfo = new UserInfo(name, imageUrl);
        this.followerCounter = new PositiveIntegerCounter(followerCount);
        this.followingCounter = new PositiveIntegerCounter(followingCount);
    }

    public User(Long id, UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalStateException("userInfo must not be null");
        }
        this.id = id;
        this.userInfo = userInfo;
        this.followingCounter = new PositiveIntegerCounter();
        this.followerCounter = new PositiveIntegerCounter();
    }

    public void follow(User targetUser) {
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

    public String getName() {
        return userInfo.getName();
    }

    public String getImageUrl() {
        return userInfo.getProfileImageUrl();
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
