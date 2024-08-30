package com.backend.post.ui;

import com.backend.common.ui.ApiResponse;
import com.backend.post.application.PostService;
import com.backend.post.application.dto.CreatePostRequestDto;
import com.backend.post.application.dto.DisLikePostRequestDto;
import com.backend.post.application.dto.LikePostRequestDto;
import com.backend.post.application.dto.UpdatePostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/post")
@RestController
public class PostController {

    private final PostService postService;


    @PostMapping
    public ApiResponse<Long> createPost(@RequestBody CreatePostRequestDto dto) {
        return ApiResponse.success(postService.createPost(dto).getId());
    }

    @PatchMapping("/{postId}")
    public ApiResponse<Long> updatePost(@PathVariable(name = "postId") Long postId,
        @RequestBody UpdatePostRequestDto dto) {
        return ApiResponse.success(postService.updatePost(postId, dto).getId());
    }

    @PostMapping("/like")
    public ApiResponse<Void> likePost(@RequestBody LikePostRequestDto dto) {
        postService.likePost(dto);
        return ApiResponse.success(null);
    }

    @PostMapping("/dislike")
    public ApiResponse<Void> dislikePost(@RequestBody DisLikePostRequestDto dto) {
        postService.dislikePost(dto);
        return ApiResponse.success(null);
    }

}
