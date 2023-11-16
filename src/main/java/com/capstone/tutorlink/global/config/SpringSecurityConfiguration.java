package com.capstone.tutorlink.global.config;

import com.capstone.tutorlink.domain.member.command.application.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
/* 시큐리티 설정 활성화 및 bean 등록 가능 */
@EnableWebSecurity
public class SpringSecurityConfiguration {

    private final AuthenticationService authenticationService;
    @Autowired
    public SpringSecurityConfiguration (AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /* 1. 비밀번호 암호화에 사용할 객체 BCryptPasswordEncoder bean 등록 */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /* 2. HTTP 요청에 대한 설정 */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                /* csrf는 기본적으로 활성화 되어 있으므로 비활성화 처리 */
                .csrf()
                .disable()
                /* 요청에 대한 권한 체크 */
                .authorizeHttpRequests()
                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
                /* hasRole 안에 전달하는 값은 "ROLE_" 가 자동으로 붙는다. */
                .antMatchers("/order/**", "/member/delete").hasAnyAuthority("ROLE_TUTEE", "ROLE_ADMIN","ROLE_TUTOR","ROLE_BOTH")
                .antMatchers("/**", "/member/login", "/member/**").permitAll()
                .antMatchers(HttpMethod.POST, "/menu/**").hasRole("ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                // 탈퇴한 사용자가 로그인하지 못하도록 로그인 페이지 권한 제거
                .antMatchers("/member/signin", "/member/**").permitAll()
                /* 위에 서술 된 내용 외의 모든 요청은 허가함 (인증 되지 않은 사용자도 요청 가능) */
                .anyRequest().permitAll()
                .and()
                /* 로그인 설정 */
                .formLogin()
                /* 로그인 페이지 설정 */
                .loginPage("/member/login")
                /* 성공 시 랜딩 페이지 설정 */
                .successForwardUrl("/")
                /* 로그인 실패 시 랜딩 페이지 설정 */
                .failureForwardUrl("/error/login")
                .and()
                /* 로그아웃 설정 */
                .logout()
                /* 로그아웃 주소 */
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                /* JSESSIONID 쿠키 삭제 */
                .deleteCookies("JSESSIONID")
                /* 세션 만료 */
                .invalidateHttpSession(true)
                /* 로그아웃 후 랜딩 페이지 */
                .logoutSuccessUrl("/")
                .and()
                /* 인증/인가 예외 처리 */
                .exceptionHandling()
                /* 인증이 필요할 때는 로그인 페이지로 이동하게 된다 */
                /* 인가 되지 않았을 때 - 권한이 없는 기능을 요청했을 때 랜딩 될 페이지 */
                .accessDeniedPage("/error/denied")
                .and()
                .build();
    }

    /* 3. 사용자 인증을 위해서 사용할 Service bean 등록, 사용할 비밀번호 인코딩 방식 설정 */
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(authenticationService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
}