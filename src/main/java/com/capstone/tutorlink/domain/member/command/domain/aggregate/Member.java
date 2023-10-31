package com.capstone.tutorlink.domain.member.command.domain.aggregate;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "MEMBER")
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private Integer memberNo;

    @Column(name = "member_id", length = 20, nullable = false)
    private String memberId;

    @Column(name = "member_pw", length = 100, nullable = false)
    private String memberPw;

    @Column(name = "member_nickname", length = 45, nullable = false)
    private String memberNickname;

    @Column(name = "member_name", length = 45, nullable = false)
    private String memberName;

    @Column(name = "member_email", length = 100, nullable = false)
    private String memberEmail;

    @Column(name = "member_gender", length = 1, nullable = false)
    private Character memberGender;

    @Column(name = "member_birthday", nullable = false)
    private Date memberBirthday;

    @Column(name = "member_enroll_date", columnDefinition = "datetime default now()", nullable = false)
    private Date memberEnrollDate; // member_enroll_date를 memberEnrollDate로 수정

    @Column(name = "member_current_status")
    private String memberCurrentStatus; // member_current_status를 memberCurrentStatus로 수정

    @Column(name = "member_phone_number", nullable = false)
    private String memberPhoneNumber; // 데이터 타입을 String으로 변경

    @Column(name = "tutor_school_authorize", length = 1, nullable = false)
    private Character tutorSchoolAuthorize;

    @Column(name = "tutor_middle_school", length = 45)
    private String tutorMiddleSchool;

    @Column(name = "tutor_high_school", length = 45)
    private String tutorHighSchool;

    @Column(name = "tutor_uni", length = 45)
    private String tutorUni;

    @Column(name = "tutor_uni_is_enrolled", length = 1)
    private Character tutorUniIsEnrolled;

    @Column(name = "tutor_major", length = 45)
    private String tutorMajor;

    @Column(name = "tutor_major_num")
    private Integer tutorMajorNum; // 데이터 타입을 Integer로 변경

    @Column(name = "tutor_authorize", length = 45)
    private String tutorAuthorize;

    @Column(name = "my_key", length = 255, nullable = false)
    private String myKey; // my_key를 myKey로 수정

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_no")
    public List<MemberRole> memberRoleList;
}
