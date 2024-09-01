package com.backend.post.repository.entity.post;

import com.backend.common.repository.TimeBaseEntity;
import com.backend.post.domain.Post;
import com.backend.post.domain.cotent.PostPublicationState;
import com.backend.user.repository.entity.UserEntity;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
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
import org.hibernate.annotations.ColumnDefault;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "community_post")
@Entity
public class PostEntity extends TimeBaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    private String content;

    @ColumnDefault("0")
    private Integer likeCount;

    @Convert(converter = PostPublicationStatConverter.class)
    private PostPublicationState state;

    @ColumnDefault("0")
    private int commentCount;

    public static PostEntity createPostEntity(Post post) {
        return new PostEntity(
            post.getId() == null ? null : post.getId(),
            UserEntity.createUserEntity(post.getAuthor()),
            post.getPostContent(),
            post.getLikeCount(),
            post.getState(),
            post.getCommentCount()
        );
    }

    public Post toPost() {
        return Post.createPost(
            id,
            author.toUser(),
            content,
            likeCount,
            state
        );
    }
}
