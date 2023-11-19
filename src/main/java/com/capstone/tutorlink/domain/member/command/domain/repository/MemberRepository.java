package com.capstone.tutorlink.domain.member.command.domain.repository;

import com.capstone.tutorlink.domain.member.command.domain.aggregate.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Optional<Member> findByMemberIdAndMemberCurrentStatus(String memberId, String memberCurrentStatus);
    Member findByMemberNo(int memberNo);

    @Query("SELECT m FROM Member m JOIN m.memberRoleList r WHERE r.authority.authorityName = 'ROLE_TUTEE'")
    Page<Member> findAllTutee(org.springframework.data.domain.Pageable pageable);


    @Query("SELECT m FROM Member m " +
            "JOIN MemberRole mr ON m.memberNo = mr.memberNo " +
            "JOIN Authority a ON mr.authorityNum = a.authorityNum " +
            "WHERE (:memberGender IS NULL OR m.memberGender = :memberGender) " +
            "AND (:tutorUni IS NULL OR m.tutorUni = :tutorUni) " +
            "AND (:myKey IS NULL OR m.myKey = :myKey) " +
            "AND a.authorityName = 'ROLE_TUTOR'")
    Page<Member> findAllTutorWithConditions(@Param("memberGender") String memberGender,
                                            @Param("tutorUni") String tutorUni,
                                            @Param("myKey") String myKey,
                                            Pageable pageable);

}
