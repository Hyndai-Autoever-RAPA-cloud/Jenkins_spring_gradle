package com.example.jenkinsgradle.domain.member.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.jenkinsgradle.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

/**
 * 통합 테스트 - 애플리케이션 전체 컨텍스트를 띄운다.
 * Controller -> Service -> Repository -> DB(H2)까지 실제로 다 탄다.
 */
@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberRepository memberRepository;

    @Test
    void register() throws Exception {
        mockMvc.perform(post("/members").param("name", "adam"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("adam"))
                .andExpect(jsonPath("$.email").value(endsWith("@mock.com")))
                .andExpect(jsonPath("$.age").isNumber());

        // 실제로 DB에 저장됐는지까지 확인
        assertThat(memberRepository.findAll())
                .extracting("name")
                .contains("adam");
    }

    @Test
    void registerWithoutName() throws Exception {
        mockMvc.perform(post("/members"))
                .andExpect(status().isBadRequest());
    }
}
