package com.backend.acceptance.steps;

import com.backend.auth.application.dto.LoginRequestDto;
import com.backend.auth.application.dto.UserAccessTokenResponseDto;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.springframework.http.MediaType;

public class LoginAcceptanceSteps {

    public static String requestLoginAndGetToken(LoginRequestDto dto) {
        return requestLogin(dto)
            .getObject("data", UserAccessTokenResponseDto.class)
            .accessToken();
    }

    public static Integer requestLoginAndGetCode(LoginRequestDto dto) {
        return requestLogin(dto)
            .get("code");
    }

    private static JsonPath requestLogin(LoginRequestDto dto) {
        return RestAssured
            .given()
            .body(dto)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .post("/login")
            .then()
            .extract()
            .jsonPath();
    }


}
