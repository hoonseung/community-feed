package com.backend.post.domain.cotent;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("[게시글 본문 테스트]")
class PostContentTest {


    @DisplayName("5 ~ 500자 사이의 게시글 본문이 주어질 때 성공적으로 검증을 통과하고 생성되는지 확인")
    @Test
    void givenValidCharacterWhenExecuteCreatingPostContent_thenShouldBeCreatedSuccessfully() {
        //given
        String content = "must be greater then 5 character";

        //when
        Content postContent = new PostContent(content);
        assertDoesNotThrow(() -> new PostContent(content));

        //then
        assertEquals(postContent.getContentText(), content);
    }

    @DisplayName("500자를 초과하는 게시글 본문이 주어지면 예외가 발생한다.")
    @ValueSource(strings = {"뷁", "왤", "삵", "쉭"})
    @ParameterizedTest(name = "{index} -> {0}")
    void givenInValidCharacterWhenExecuteCreatingPostContent_thenShouldBeCreatedSuccessfully(
        String keyword) {
        //given
        String content = keyword.repeat(501);

        //when & then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content),
            "Content length must be between 5 and 500 characters");
    }

    @DisplayName("5글자 이하 게시글 본문이 주어지면 예외가 발생한다.")
    @Test
    void givenInValidCharacterWhenExecuteCreatingPostContent_thenShouldBeCreatedSuccessfully() {
        //given
        String content = "test";

        //when & then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content),
            "Content length must be between 5 and 500 characters");
    }

    @DisplayName("게시글 본문이 주어지지 않으면 예외가 발생한다.")
    @Test
    void givenNullCharacterWhenExecuteCreatingPostContent_thenShouldBeCreatedSuccessfully() {
        //given & when & then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(null),
            "Content text cannot be null");
    }


    @DisplayName("업데이트 하려는 5 ~ 500자 사이의 게시글 본문이 주어질 때 성공적으로 업데이트 되고 업데이트 날짜 변경이 잘되는지 확인")
    @Test
    void givenValidCharacterWhenExecuteUpdatingPostContent_thenShouldBeCreatedSuccessfully() {
        //given
        String content = "must be greater then 5 character";
        String updateContent = "I want to updating this content";
        Content postContent = new PostContent(content);
        LocalDateTime pastTime = postContent.getDataTimeInfo().getDateTime();

        //when
        assertDoesNotThrow(() -> postContent.updateContent(updateContent));

        //then
        assertEquals(postContent.getContentText(), updateContent);
        assertTrue(postContent.getDataTimeInfo().getDateTime().isAfter(pastTime));
        assertTrue(postContent.getDataTimeInfo().isModified());
    }

}