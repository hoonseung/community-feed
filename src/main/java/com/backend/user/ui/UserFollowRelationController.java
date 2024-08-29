package com.backend.user.ui;

import com.backend.common.ui.ApiResponse;
import com.backend.user.application.UserFollowRelationService;
import com.backend.user.application.dto.FollowUserRequestDto;
import com.backend.user.application.dto.GetUserListResponseDto;
import com.backend.user.application.dto.UnFollowUserRequestDto;
import com.backend.user.repository.jpa.JpaUserListQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/relation")
@RestController
public class UserFollowRelationController {

    private final UserFollowRelationService userFollowRelationService;



    @PostMapping("/follow")
    public ApiResponse<Void> followUser(@RequestBody FollowUserRequestDto dto) {
        userFollowRelationService.follow(dto);
        return ApiResponse.success(null);
    }

    @PostMapping("/unfollow")
    public ApiResponse<Void> unfollowUser(@RequestBody UnFollowUserRequestDto dto) {
        userFollowRelationService.unFollow(dto);
        return ApiResponse.success(null);
    }


}
