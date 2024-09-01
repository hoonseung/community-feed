package com.backend.post.repository.jpa;

import com.backend.post.ui.dto.GetCommentContentResponseDto;
import java.util.List;

public interface JpaCommentListQueryRepository {

    List<GetCommentContentResponseDto> getCommentsResponse(Long postId, Long lastCommentId);
}
