package com.backend.post.domain.common;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[DateTimeInfo 테스트]")
class DateTimeInfoTest {


    @DisplayName("업데이트하였을 때 정상적으로 시간이 등록되고 업데이트 마크가 표시되는지 확인")
    @Test
    void whenExecuteUpdate_thenShouldUpdatingDateTimeAndMarking() {
        //given
        DateTimeInfo dateTimeInfo = new DateTimeInfo();
        LocalDateTime now = dateTimeInfo.getDateTime();
        //when
        dateTimeInfo.updateDateTime();

        //then
        assertTrue(dateTimeInfo.isModified());
        System.out.println(dateTimeInfo.getDateTime());
        System.out.println(now);
    }
}