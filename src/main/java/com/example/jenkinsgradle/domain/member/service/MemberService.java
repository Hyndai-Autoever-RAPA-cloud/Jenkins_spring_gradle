package com.example.jenkinsgradle.domain.member.service;

import com.example.jenkinsgradle.domain.member.entity.Member;
import com.example.jenkinsgradle.domain.member.repository.MemberRepository;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {
    private static final int MOCK_MIN_AGE = 20;
    private static final int MOCK_MAX_AGE = 40;

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 이름만 받아서 나머지 값은 mock으로 채워 저장한다.
     */
    @Transactional
    public Member register(String name) {
        Member member = Member.builder()
                .name(name)
                .email(mockEmail(name))
                .age(mockAge())
                .build();
        return memberRepository.save(member);
    }

    // email에 unique 제약이 있어서 같은 이름이 여러 번 들어와도 충돌하지 않도록 suffix를 붙인다
    private String mockEmail(String name) {
        String prefix = name.toLowerCase().replaceAll("[^a-z0-9]", "");
        if (prefix.isEmpty()) {
            prefix = "member";
        }
        return prefix + "-" + UUID.randomUUID().toString().substring(0, 8) + "@mock.com";
    }

    private int mockAge() {
        return ThreadLocalRandom.current().nextInt(MOCK_MIN_AGE, MOCK_MAX_AGE);
    }
}
