package com.example.jenkinsgradle;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

/**
 * 서비스 단위 테스트 - 스프링 컨텍스트를 아예 띄우지 않는다. (가장 빠름)
 */
class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void addition() {
        assertThat(calculator.addition(2, 3)).isEqualTo(5);
    }

    @Test
    void subtraction() {
        assertThat(calculator.subtraction(5, 3)).isEqualTo(2);
    }

    @Test
    void multiplication() {
        assertThat(calculator.multiplication(4, 3)).isEqualTo(12);
    }
}
