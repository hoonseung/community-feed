package com.backend.acceptance.utils;

import com.backend.acceptance.steps.UserAcceptanceSteps;
import com.backend.user.application.dto.CreateUserRequestDto;
import com.backend.user.application.dto.FollowUserRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("test")
@Slf4j
@Component
public class DataLoader {


    public void loadData() {
        log.info("Load data");
        CreateUserRequestDto userRequestDto = new CreateUserRequestDto("testUser", "");
        UserAcceptanceSteps.requestCreateUser(userRequestDto);
        UserAcceptanceSteps.requestCreateUser(userRequestDto);
        UserAcceptanceSteps.requestCreateUser(userRequestDto);

        FollowUserRequestDto followDto1 = new FollowUserRequestDto(1L, 2L);
        FollowUserRequestDto followDto2 = new FollowUserRequestDto(1L, 3L);
        UserAcceptanceSteps.requestFollowUser(followDto1);
        UserAcceptanceSteps.requestFollowUser(followDto2);
    }
}
