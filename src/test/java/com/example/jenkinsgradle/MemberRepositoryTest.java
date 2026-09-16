package com.example.jenkinsgradle;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.jenkinsgradle.domain.member.entity.Member;
import com.example.jenkinsgradle.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        Member saved = memberRepository.save(
            Member.builder().age(1).email("itstudy@kakao.com").name("adam").build()
        );

        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo("adam");
        assertThat(saved.getEmail()).isEqualTo("itstudy@kakao.com");
        assertThat(saved.getAge()).isEqualTo(1);
    }
}
