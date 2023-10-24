package com.capstone.tutorlink.domain.member.command.application.dto;

import lombok.*;
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
    @NotNull(message = "아이디는 반드시 입력되어야 합니다.") // null 허용하지 않음. "", " " 허용
    @NotBlank(message = "아이디는 공백일 수 없습니다.") // null, "", " " 모두 허용하지 않음
    private String memberId;
    private String memberPw;
    private String memberNickname;
    @NotNull(message = "이름은 반드시 입력되어야 합니다.")
    // @Size(min=, max=) 길이 제한, @Min, @Max로도 표현 가능
    @Size(min=2, message="이름은 2글자 이상 입력해야 합니다.")
    private String memberName;
    private String memberEmail;
    private char memberGender;
    private Date memberBirthday;
    private Date enrollDate;
    private String memberStatus;
    private int memberPhoneNumber;
    private char tutorSchoolAuthorize;
    private String tutorMiddleSchool;
    private String tutorHighSchool;
    private String tutorUni;
    private char tutorUniIsEnrolled;
    private String tutorMajor;
    private Integer tutorMajorNum;
    private String tutorAuthorize;
    private String key;
    private Collection<? extends GrantedAuthority> authorities;

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPw() {
        return memberPw;
    }

    public void setMemberPw(String memberPw) {
        this.memberPw = memberPw;
    }

    public String getMemberNickname() {
        return memberNickname;
    }

    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public char getMemberGender() {
        return memberGender;
    }

    public void setMemberGender(char memberGender) {
        this.memberGender = memberGender;
    }

    public Date getMemberBirthday() {
        return memberBirthday;
    }

    public void setMemberBirthday(Date memberBirthday) {
        this.memberBirthday = memberBirthday;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }

    public int getMemberPhoneNumber() {
        return memberPhoneNumber;
    }

    public void setMemberPhoneNumber(int memberPhoneNumber) {
        this.memberPhoneNumber = memberPhoneNumber;
    }

    public char getTutorSchoolAuthorize() {
        return tutorSchoolAuthorize;
    }

    public void setTutorSchoolAuthorize(char tutorSchoolAuthorize) {
        this.tutorSchoolAuthorize = tutorSchoolAuthorize;
    }

    public String getTutorMiddleSchool() {
        return tutorMiddleSchool;
    }

    public void setTutorMiddleSchool(String tutorMiddleSchool) {
        this.tutorMiddleSchool = tutorMiddleSchool;
    }

    public String getTutorHighSchool() {
        return tutorHighSchool;
    }

    public void setTutorHighSchool(String tutorHighSchool) {
        this.tutorHighSchool = tutorHighSchool;
    }

    public String getTutorUni() {
        return tutorUni;
    }

    public void setTutorUni(String tutorUni) {
        this.tutorUni = tutorUni;
    }

    public char getTutorUniIsEnrolled() {
        return tutorUniIsEnrolled;
    }

    public void setTutorUniIsEnrolled(char tutorUniIsEnrolled) {
        this.tutorUniIsEnrolled = tutorUniIsEnrolled;
    }

    public String getTutorMajor() {
        return tutorMajor;
    }

    public void setTutorMajor(String tutorMajor) {
        this.tutorMajor = tutorMajor;
    }

    public Integer getTutorMajorNum() {
        return tutorMajorNum;
    }

    public void setTutorMajorNum(Integer tutorMajorNum) {
        this.tutorMajorNum = tutorMajorNum;
    }

    public String getTutorAuthorize() {
        return tutorAuthorize;
    }

    public void setTutorAuthorize(String tutorAuthorize) {
        this.tutorAuthorize = tutorAuthorize;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

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
