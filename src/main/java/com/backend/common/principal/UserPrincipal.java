package com.backend.common.principal;

import com.backend.auth.domain.UserRole;
import lombok.Getter;

@Getter
public class UserPrincipal {

    private Long userId;
    private UserRole userRole;


    public UserPrincipal(Long userId, String userRole) {
        this.userId = userId;
        this.userRole = UserRole.valueOf(userRole);
    }
}
