package com.backend.post.ui.dto;

import com.backend.post.repository.entity.post.PostEntity;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetPostContentResponseDto extends GetContentResponseDto {

    private Integer commentCount;



    public static GetPostContentResponseDto from(PostEntity postEntity){
        return GetPostContentResponseDto.builder()
            .id(postEntity.getId())
            .content(postEntity.getContent())
            .userId(postEntity.getAuthor().getId())
            .userName(postEntity.getAuthor().getUserName())
            .userProfileImage(postEntity.getAuthor().getProfileImage())
            .createdAt(postEntity.getCreatedAt())
            .modifiedAt(postEntity.getModifiedAt())
            .likeCount(postEntity.getLikeCount())
            .commentCount(postEntity.getCommentCount())
            .build();
    }
}
