package com.backend.fake;

import com.backend.post.application.CommentService;
import com.backend.post.application.PostService;
import com.backend.post.application.interfaces.CommentRepository;
import com.backend.post.application.interfaces.LikeRepository;
import com.backend.post.application.interfaces.PostRepository;
import com.backend.post.repository.FakeCommentRepository;
import com.backend.post.repository.FakeLikeRepository;
import com.backend.post.repository.FakePostRepository;
import com.backend.user.application.UserFollowRelationService;
import com.backend.user.application.UserService;
import com.backend.user.application.interfaces.UserFollowRelationRepository;
import com.backend.user.application.interfaces.UserRepository;
import com.backend.user.repository.FakeFollowRelationRepository;
import com.backend.user.repository.FakeUserRepository;

public class FakeObjectFactory {

    private static final UserRepository userRepository = new FakeUserRepository();
    private static final UserFollowRelationRepository userFollowRelationRepository = new FakeFollowRelationRepository();
    private static final PostRepository postRepository = new FakePostRepository();
    private static final CommentRepository commentRepository = new FakeCommentRepository();
    private static final LikeRepository likeRepository = new FakeLikeRepository();


    private static final UserService userService = new UserService(userRepository);
    private static final UserFollowRelationService userFollowRelationService = new UserFollowRelationService(
        userService, userFollowRelationRepository);
    private static final PostService postService = new PostService(userService, likeRepository,
        postRepository);
    private static final CommentService commentService = new CommentService(postService,
        userService, commentRepository, likeRepository);

    public static FakeObjectFactory fakeObjectFactory;

    private FakeObjectFactory() {
    }


    public static UserService userService() {
        return userService;
    }


    public static UserFollowRelationService userFollowRelationService() {
        return userFollowRelationService;
    }


    public static PostService postService() {
        return postService;
    }


    public static CommentService commentService() {
        return commentService;
    }


}
