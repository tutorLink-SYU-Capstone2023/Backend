package com.capstone.tutorlink.domain.member.command.domain.repository;

import com.capstone.tutorlink.domain.member.command.domain.aggregate.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    /* 전달 받은 member id를 통해 Member entity 1개를 조회하는 메소드 - 로그인 */
    @EntityGraph(attributePaths = {"memberRoleList", "memberRoleList.authority"})
    Optional<Member> findByMemberId(String memberId);
}
