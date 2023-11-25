package com.capstone.tutorlink.domain.member.command.domain.repository;

import com.capstone.tutorlink.domain.member.command.domain.aggregate.LikedMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikedMemberRepository extends JpaRepository<LikedMember, Integer> {
    void deleteByMember_MemberNoAndLikedMember_MemberNo(int memberId, int likedMemberId);
    // 좋아요가 많이 눌린 상위 3명의 튜터 정보 조회
    @Query("SELECT lm.likedMember.memberNo, COUNT(lm.likedMember.memberNo) AS likeCount " +
            "FROM LikedMember lm " +
            "GROUP BY lm.likedMember.memberNo " +
            "ORDER BY likeCount DESC")
    List<Object[]> findTop3LikedTutors();

}


