package com.backend.post.application;

import com.backend.post.application.dto.CreatePostRequestDto;
import com.backend.post.application.dto.DisLikePostRequestDto;
import com.backend.post.application.dto.LikePostRequestDto;
import com.backend.post.application.dto.UpdatePostRequestDto;
import com.backend.post.application.interfaces.LikeRepository;
import com.backend.post.application.interfaces.PostRepository;
import com.backend.post.domain.Post;
import com.backend.user.application.UserService;
import com.backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {


    private final UserService userService;
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;


    public Post createPost(CreatePostRequestDto dto) {
        User user = userService.getUser(dto.userId());
        return postRepository.save(Post.createPost(user, dto.content(), dto.state()));
    }


    public Post updatePost(Long postId, UpdatePostRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Post post = getPost(postId);
        post.updatePost(user, dto.content(), dto.state());

        return postRepository.save(post);
    }


    public Post getPost(Long id) {
        return postRepository.findById(id);
    }

    public void likePost(LikePostRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Post post = getPost(dto.postId());

        if (likeRepository.checkLike(post, user)) {
            throw new IllegalArgumentException("this user already pushed like button post");
        }

        post.like(user);
        likeRepository.save(post, user);
    }


    public void dislikePost(DisLikePostRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Post post = getPost(dto.postId());

        if (!likeRepository.checkLike(post, user)) {
            throw new IllegalArgumentException("this user never pushed like button post");
        }

        post.dislike(user);
        likeRepository.delete(post, user);
    }
}
