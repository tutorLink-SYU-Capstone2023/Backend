package com.capstone.tutorlink.domain.member.command.application.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO implements UserDetails {
    private int memberNo;

    @NotNull(message = "아이디는 반드시 입력되어야 합니다.")
    @NotBlank(message = "아이디는 공백일 수 없습니다.")
    private String memberId;

    private String memberPw;
    private String memberNickname;

    @NotNull(message = "이름은 반드시 입력되어야 합니다.")
    @Size(min=2, message="이름은 2글자 이상 입력해야 합니다.")
    private String memberName;

    private String memberEmail;
    private char memberGender;

    @NotNull(message = "생년월일은 반드시 입력되어야 합니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date memberBirthday;

    private Date memberEnrollDate; // member_enroll_date를 memberEnrollDate로 수정

    private String memberCurrentStatus; // member_current_status를 memberCurrentStatus로 수정
    private String memberPhoneNumber; // 데이터 타입을 String으로 변경

    private char tutorSchoolAuthorize;
    private String tutorMiddleSchool;
    private String tutorHighSchool;
    private String tutorUni;
    private char tutorUniIsEnrolled;
    private String tutorMajor;
    private Integer tutorMajorNum; // 데이터 타입을 Integer로 변경
    private String tutorAuthorize;
    private String myKey; // my_key를 myKey로 수정

    private Collection<? extends GrantedAuthority> authorities;

    public void setMemberEnrollDate(Date memberEnrollDate) {
        this.memberEnrollDate = memberEnrollDate;
    }

    public void setMemberCurrentStatus(String memberCurrentStatus) {
        this.memberCurrentStatus = memberCurrentStatus;
    }

    public void setMemberPhoneNumber(String memberPhoneNumber) {
        this.memberPhoneNumber = memberPhoneNumber;
    }

    public void setMyKey(String myKey) {
        this.myKey = myKey;
    }

    // 나머지 getter, setter, 메소드는 그대로 유지합니다.

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return memberPw;
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
