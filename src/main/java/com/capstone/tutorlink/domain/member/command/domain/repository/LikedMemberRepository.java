package com.capstone.tutorlink.domain.member.command.domain.repository;

import com.capstone.tutorlink.domain.member.command.domain.aggregate.LikedMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedMemberRepository extends JpaRepository<LikedMember, Integer> {

    void deleteByMemberIdAndLikedMemberId(Integer memberId, Integer likedMemberId);
}
