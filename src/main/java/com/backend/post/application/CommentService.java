package com.backend.post.application;

import com.backend.post.application.dto.CreateCommentRequestDto;
import com.backend.post.application.dto.DisLikeCommentRequestDto;
import com.backend.post.application.dto.LikeCommentRequestDto;
import com.backend.post.application.dto.UpdateCommentRequestDto;
import com.backend.post.application.interfaces.CommentRepository;
import com.backend.post.application.interfaces.LikeRepository;
import com.backend.post.domain.Post;
import com.backend.post.domain.comment.Comment;
import com.backend.user.application.UserService;
import com.backend.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {


    private final PostService postService;
    private final UserService userService;
    private final CommentRepository commentRepository;
    private final LikeRepository likeRepository;


    public Comment createComment(CreateCommentRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Post post = postService.getPost(dto.postId());

        return commentRepository.save(Comment.createComment(user, dto.content(), post));
    }


    public Comment updateComment(Long commentId, UpdateCommentRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Comment comment = getComment(commentId);

        comment.updateComment(user, dto.content());

        return commentRepository.save(comment);
    }


    public void likeComment(LikeCommentRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Comment comment = getComment(dto.commentId());

        comment.like(user);

        if (likeRepository.checkLike(comment, user)) {
            throw new IllegalArgumentException("this user already pushed like button comment");
        }

        likeRepository.save(comment, user);
    }


    public void dislikeComment(DisLikeCommentRequestDto dto) {
        User user = userService.getUser(dto.userId());
        Comment comment = getComment(dto.commentId());

        comment.dislike(user);

        if (!likeRepository.checkLike(comment, user)) {
            throw new IllegalArgumentException("this user never pushed like button comment");
        }
        likeRepository.delete(comment, user);
    }


    public Comment getComment(Long id) {
        return commentRepository.findById(id);
    }
}

