package com.backend.post.repository.entity.like;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LikeRelationIdEntity {

    private Long targetId;

    private Long userId;

    private String targetType;

}
