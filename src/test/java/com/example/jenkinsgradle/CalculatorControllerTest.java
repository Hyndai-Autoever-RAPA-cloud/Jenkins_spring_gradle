package com.example.jenkinsgradle;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * 슬라이스 테스트 - 웹 계층(컨트롤러)만 띄운다.
 * DB, Service 빈은 로딩되지 않으므로 의존하는 빈은 @MockitoBean으로 직접 넣어줘야 한다.
 */
@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    Calculator calculator;

    @Test
    void index() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("health check"));
    }

    @Test
    void add() throws Exception {
        given(calculator.addition(2, 3)).willReturn(5);

        mockMvc.perform(get("/add").param("num1", "2").param("num2", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));
    }

    @Test
    void addWithoutParam() throws Exception {
        mockMvc.perform(get("/add").param("num1", "2"))
                .andExpect(status().isBadRequest());
    }
}
