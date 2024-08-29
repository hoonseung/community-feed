package com.backend.user.application;

import com.backend.user.application.dto.FollowUserRequestDto;
import com.backend.user.application.dto.UnFollowUserRequestDto;
import com.backend.user.application.interfaces.UserFollowRelationRepository;
import com.backend.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserFollowRelationService {

    private final UserService userService;
    private final UserFollowRelationRepository userFollowRelationRepository;

    public UserFollowRelationService(UserService userService,
        UserFollowRelationRepository userFollowRelationRepository) {
        this.userService = userService;
        this.userFollowRelationRepository = userFollowRelationRepository;
    }


    public void follow(FollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (userFollowRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException("already following");
        }

        user.follow(targetUser);
        userFollowRelationRepository.save(user, targetUser);
    }


    public void unFollow(UnFollowUserRequestDto dto) {
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if (!userFollowRelationRepository.isAlreadyFollow(user, targetUser)) {
            throw new IllegalArgumentException("follow info not founded");
        }

        user.unfollow(targetUser);
        userFollowRelationRepository.delete(user, targetUser);
    }
}
