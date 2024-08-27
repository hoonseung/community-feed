package com.backend.post.repository.entity.comment;

import com.backend.common.repository.TimeBaseEntity;
import com.backend.post.domain.comment.Comment;
import com.backend.post.repository.entity.post.PostEntity;
import com.backend.user.repository.entity.UserEntity;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
@Table(name = "community_comment")
@Entity
public class CommentEntity extends TimeBaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private PostEntity post;

    private String content;
    private Integer likeCount;


    public static CommentEntity createPost(Comment comment) {
        return new CommentEntity(
            null,
            UserEntity.createUserEntity(comment.getAuthor()),
            PostEntity.createPostEntity(comment.getPost()),
            comment.getCommentContent(),
            comment.getLikeCount()
        );
    }

    public Comment toComment() {
        return Comment.createComment(
            id,
            author.toUser(),
            content,
            post.toPost(),
            likeCount
        );
    }
}
