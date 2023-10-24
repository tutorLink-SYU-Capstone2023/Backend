package com.capstone.tutorlink.domain.member.command.domain.aggregate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "MEMBER")
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private int memberNo;

    @Column(name = "member_id", length = 20, nullable = false)
    private String memberId;

    @Column(name = "member_pw", length = 100, nullable = false)
    private String memberPw;

    @Column(name = "member_nickname", length = 45, nullable = false)
    private String memberNickname;

    @Column(name = "member_name", length = 45, nullable = false)
    private String memberName;

    @Column(name = "member_email", length = 45, nullable = false)
    private String memberEmail;

    @Column(name = "member_gender", length = 1, nullable = false)
    private char memberGender;

    @Column(name = "member_birthday", nullable = false)
    private Date memberBirthday;

    @Column(name = "enroll_date", columnDefinition = "datetime default now()", nullable = false)
    private Date enrollDate;

    @Column(name = "member_status", length = 2, columnDefinition = "default 'Y'", nullable = false)
    private String memberStatus;

    @Column(name = "member_phonenumber", nullable = false)
    private int memberPhoneNumber;

    @Column(name = "tutor_school_authorize", length = 1, nullable = false)
    private char tutorSchoolAuthorize;

    @Column(name = "tutor_midleschool", length = 45)
    private String tutorMiddleSchool;

    @Column(name = "tutor_highschool", length = 45)
    private String tutorHighSchool;

    @Column(name = "tutor_uni", length = 45)
    private String tutorUni;

    @Column(name = "tutor_uni_is_enrolled", length = 1)
    private char tutorUniIsEnrolled;

    @Column(name = "tutor_major", length = 45)
    private String tutorMajor;

    @Column(name = "tutor_major_num")
    private Integer tutorMajorNum;

    @Column(name = "tutor_authorize", length = 45)
    private String tutorAuthorize;

    @Column(name = "key", length = 255, nullable = false)
    private String key;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_no")
    public List<MemberRole> memberRoleList;
}
