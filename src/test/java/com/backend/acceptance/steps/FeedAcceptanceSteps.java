package com.backend.acceptance.steps;

import com.backend.post.application.dto.CreatePostRequestDto;
import com.backend.post.ui.dto.GetPostContentResponseDto;
import io.restassured.RestAssured;
import java.util.List;
import org.springframework.http.MediaType;

public class FeedAcceptanceSteps {


    public static Long requestCreatePost(CreatePostRequestDto dto) {
        return RestAssured
            .given().log().all()
            .body(dto)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().post("/post")
            .then().log().all()
            .extract()
            .jsonPath()
            .getObject("data", Long.class);
    }


    public static List<GetPostContentResponseDto> requestGetFeeds(Long userId) {
        return RestAssured
            .given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().get("/feed/{userId}", userId)
            .then().log().all()
            .extract()
            .jsonPath()
            .getList("data", GetPostContentResponseDto.class);
    }


}
