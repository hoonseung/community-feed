package com.backend.post.ui;

import com.backend.common.ui.ApiResponse;
import com.backend.post.repository.jpa.JpaCommentListQueryRepository;
import com.backend.post.repository.post_queue.UserPostQueueQueryRepository;
import com.backend.post.ui.dto.GetCommentContentResponseDto;
import com.backend.post.ui.dto.GetPostContentResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/feed")
@RestController
public class FeedController {

    private final UserPostQueueQueryRepository userPostQueueCommandRepository;
    private final JpaCommentListQueryRepository jpaCommentListQueryRepository;

    @GetMapping("/{userId}")
    public ApiResponse<List<GetPostContentResponseDto>> getPostsResponse(
        @PathVariable(name = "userId") Long userId,
        @RequestParam(name = "lastPostId", required = false) Long lastPostId) {
        return ApiResponse.success(
            userPostQueueCommandRepository.getPostsResponse(userId, lastPostId));
    }

    @GetMapping("{postId}/comments")
    public ApiResponse<List<GetCommentContentResponseDto>> getCommentsResponse(
        @PathVariable(name = "postId") Long postId,
        @RequestParam(name = "lastCommentId", required = false) Long lastCommentId) {
        return ApiResponse.success(
            jpaCommentListQueryRepository.getCommentsResponse(postId, lastCommentId));
    }

}
