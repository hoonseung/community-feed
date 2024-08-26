package com.backend.common.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("[카운트 증감기 테스트]")
class PositiveIntegerCounterTest {

    @DisplayName("카운트 증가 정상")
    @Test
    void whenCounterExecute_thenShouldIncrementedCount() {
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        //when
        counter.increment();

        //then
        assertEquals(1, counter.getCount());
    }

    @DisplayName("카운트 감소 정상")
    @Test
    void whenCounterExecute_thenShouldDecrementedCount() {
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        counter.increment();
        //when
        counter.decrement();

        //then
        assertEquals(0, counter.getCount());
    }

    @DisplayName("현재 카운트가 0일때 감소하면 예외를 발생시킨다.")
    @Test
    void whenCountLessThanZero_thenShouldThrowException() {
        //given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        //when
        assertThrows(IllegalArgumentException.class, counter::decrement,
            "Count cannot be negative");

        //then
    }
}