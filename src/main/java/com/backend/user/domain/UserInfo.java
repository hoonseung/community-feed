package com.backend.user.domain;

import java.util.Objects;

public class UserInfo {

    private final String name;
    private final String profileImageUrl;

    public UserInfo(String name, String profileImageUrl) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
        this.profileImageUrl = profileImageUrl;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof UserInfo userInfo)) {
            return false;
        }
        return Objects.equals(name, userInfo.name) && Objects.equals(
            profileImageUrl, userInfo.profileImageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, profileImageUrl);
    }
}
