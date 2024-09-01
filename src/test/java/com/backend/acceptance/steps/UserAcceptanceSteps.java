package com.backend.acceptance.steps;

import com.backend.user.application.dto.CreateUserRequestDto;
import com.backend.user.application.dto.FollowUserRequestDto;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.springframework.http.MediaType;

public class UserAcceptanceSteps {

    public static ExtractableResponse<Response> requestCreateUser(CreateUserRequestDto dto) {
        return RestAssured
            .given()
            .body(dto)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().post("/users")
            .then()
            .extract();
    }


    public static ExtractableResponse<Response> requestFollowUser(FollowUserRequestDto dto) {
        return RestAssured
            .given()
            .body(dto)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when().post("/relation/follow")
            .then()
            .extract();
    }


}
