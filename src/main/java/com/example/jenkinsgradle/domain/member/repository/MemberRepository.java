package com.example.jenkinsgradle.domain.member.repository;

import com.example.jenkinsgradle.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
