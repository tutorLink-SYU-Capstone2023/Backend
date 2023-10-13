package com.ohgiraffers.tutorlinktest.member.respository;

import com.ohgiraffers.tutorlinktest.member.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    /* 전달 받은 member id를 통해 Member entity 1개를 조회하는 메소드 - 로그인 */
    @EntityGraph(attributePaths = {"memberRoleList", "memberRoleList.authority"})
    Optional<Member> findByMemberId(String memberId);
}
