package com.backend.post.ui;

import com.backend.common.ui.ApiResponse;
import com.backend.post.application.CommentService;
import com.backend.post.application.dto.CreateCommentRequestDto;
import com.backend.post.application.dto.DisLikeCommentRequestDto;
import com.backend.post.application.dto.LikeCommentRequestDto;
import com.backend.post.application.dto.UpdateCommentRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/comment")
@RestController
public class CommentController {

    private final CommentService commentService;


    @PostMapping
    public ApiResponse<Long> createPost(@RequestBody CreateCommentRequestDto dto) {
        return ApiResponse.success(commentService.createComment(dto).getId());
    }

    @PatchMapping("/{commentId}")
    public ApiResponse<Long> updateComment(@PathVariable(name = "commentId") Long commentId,
        @RequestBody UpdateCommentRequestDto dto) {
        return ApiResponse.success(commentService.updateComment(commentId, dto).getId());
    }

    @PostMapping("/like")
    public ApiResponse<Void> likeComment(@RequestBody LikeCommentRequestDto dto) {
        commentService.likeComment(dto);
        return ApiResponse.success(null);
    }

    @PostMapping("/dislike")
    public ApiResponse<Void> dislikeComment(@RequestBody DisLikeCommentRequestDto dto) {
        commentService.dislikeComment(dto);
        return ApiResponse.success(null);
    }


}
