package com.ohgiraffers.tutorlinktest.member.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

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
    private String memberPwd;
    @NotNull(message = "이름은 반드시 입력되어야 합니다.")
    // @Size(min=, max=) 길이 제한, @Min, @Max로도 표현 가능
    @Size(min=2, message="이름은 2글자 이상 입력해야 합니다.")
    private String memberName;
    private Collection<? extends GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return memberPwd;
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
