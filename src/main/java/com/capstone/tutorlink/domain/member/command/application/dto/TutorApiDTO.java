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

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class TutorApiDTO implements UserDetails {
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
    private String address;
    private String memberGender;

    @NotNull(message = "생년월일은 반드시 입력되어야 합니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date memberBirthday;

    private Date memberEnrollDate; // member_enroll_date를 memberEnrollDate로 수정

    private String memberCurrentStatus = "A"; // member_current_status를 memberCurrentStatus로 수정
    private String memberPhoneNumber; // 데이터 타입을 String으로 변경
    private String tutorSchoolAuthorize ="N";
    private String tutorMiddleSchool;
    private String tutorHighSchool;
    private String tutorUni;
    private String tutorUniIsEnrolled;
    private String tutorMajor;
    private Integer tutorMajorNum; // 데이터 타입을 Integer로 변경
    private String tutorAuthorize;

    private String selectedUnivName; // 사용자가 선택한 대학
    private Collection<? extends GrantedAuthority> authorities;
    private String selectedField; // 사용자가 선택한 field 값
    private String myKey; // my_key를 myKey로 수정
    // address1, address2 필드 추가

    private String zipCode;
    private String address1;
    private String address2;

    private String profileImgName;
    private String profileImgPath;



    public String getSelectedField() {
        return selectedField;
    }
    public String getSelectedUnivName() {
        return selectedUnivName;
    }

    public void setSelectedField(String selectedField) {
        this.selectedField = selectedField;
    }

    public int getMemberNo() {
        return memberNo;
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


    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
