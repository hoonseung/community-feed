package com.backend.post.application;

import com.backend.fake.FakeObjectFactory;
import com.backend.post.application.dto.CreateCommentRequestDto;
import com.backend.post.application.dto.CreatePostRequestDto;
import com.backend.post.application.dto.UpdateCommentRequestDto;
import com.backend.post.domain.Post;
import com.backend.post.domain.comment.Comment;
import com.backend.post.domain.cotent.PostPublicationState;
import com.backend.user.application.UserService;
import com.backend.user.application.dto.UserDto;
import com.backend.user.domain.User;
import com.backend.user.domain.UserInfo;

public class PostApplicationTestTemplate {

    protected final UserService userService = FakeObjectFactory.userService();
    protected final PostService postService = FakeObjectFactory.postService();
    protected final CommentService commentService = FakeObjectFactory.commentService();


    protected User user1 = userService.createUser(UserDto.of(null, new UserInfo("홍길동", "")));
    protected User user2 = userService.createUser(UserDto.of(null, new UserInfo("이순신", "")));

    protected CreatePostRequestDto createPostRequestDto = new CreatePostRequestDto(user1.getId(),
        "test content", PostPublicationState.PUBLIC);
    protected Post post = postService.createPost(createPostRequestDto);


    protected CreateCommentRequestDto createCommentRequestDto = new CreateCommentRequestDto(
        post.getId(), user2.getId(),
        "test content");
    Comment comment = commentService.createComment(createCommentRequestDto);


    protected UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(
        comment.getId(), user2.getId(),
        "updated content");

}
