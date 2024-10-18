package com.backend.acceptance.utils;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class AcceptanceTestTemplate {

    @Autowired
    private DataBaseCleanUp dataBaseCleanUp;
    @Autowired
    private DataLoader dataLoader;


    @BeforeEach
    protected void testBefore() {
        dataBaseCleanUp.execute();
        dataLoader.loadData();
    }


    protected String getEmailToken(String email) {
        return dataLoader.getEmailToken(email);
    }

    protected void cleanup() {
        dataBaseCleanUp.execute();
    }

    protected boolean isEmailVerified(String email) {
        return dataLoader.isEmailVerified(email);
    }

    protected Long getUserId(String email) {
        return dataLoader.getUserId(email);
    }
}
