package com.capstone.tutorlink.domain.member.command.domain.repository;

import com.capstone.tutorlink.domain.member.command.domain.aggregate.LikedMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikedMemberRepository extends JpaRepository<LikedMember, Integer> {
    void deleteByMember_MemberNoAndLikedMember_MemberNo(int memberId, int likedMemberId);
}


