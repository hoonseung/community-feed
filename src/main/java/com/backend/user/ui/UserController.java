package com.backend.user.ui;

import com.backend.common.ui.ApiResponse;
import com.backend.user.application.UserService;
import com.backend.user.application.dto.CreateUserRequestDto;
import com.backend.user.application.dto.GetUserListResponseDto;
import com.backend.user.application.dto.GetUserResponseDto;
import com.backend.user.domain.User;
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
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;
    private final JpaUserListQueryRepository jpaUserListQueryRepository;

    @PostMapping
    public ApiResponse<Long> createUser(@RequestBody CreateUserRequestDto dto) {
        User user = userService.createUser(dto);
        return ApiResponse.success(user.getId());
    }

    @GetMapping("/{userId}/followers")
    public ApiResponse<List<GetUserListResponseDto>> getFollowers(@PathVariable Long userId) {
        return ApiResponse.success(jpaUserListQueryRepository.getFollowerUserListById(userId));
    }

    @GetMapping("/{userId}/followings")
    public ApiResponse<List<GetUserListResponseDto>> getFollowings(@PathVariable Long userId) {
        return ApiResponse.success(jpaUserListQueryRepository.getFollowingUserListById(userId));
    }

    @GetMapping("/{userId}")
    public ApiResponse<GetUserResponseDto> getUserProfile(@PathVariable Long userId) {
        return ApiResponse.success(userService.getUserProfile(userId));
    }

}
