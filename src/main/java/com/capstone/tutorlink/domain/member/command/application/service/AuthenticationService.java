package com.capstone.tutorlink.domain.member.command.application.service;

import com.capstone.tutorlink.domain.member.command.application.dto.MemberDTO;
import com.capstone.tutorlink.domain.member.command.domain.aggregate.Member;
import com.capstone.tutorlink.domain.member.command.domain.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthenticationService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Autowired
    public AuthenticationService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("로그인 요청 id : {}", username);
        Member member = memberRepository.findByMemberId(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found"));
        List<GrantedAuthority> authorities =
                member.getMemberRoleList().stream()
                        .map(memberRole -> new SimpleGrantedAuthority(memberRole.getAuthority().getAuthorityName()))
                        .collect(Collectors.toList());
        log.info("member : {}", member);
        log.info("authorities : {}", authorities);
        return new MemberDTO(member.getMemberNo(), member.getMemberId(), member.getMemberPwd(),
                member.getMemberName(), authorities);
    }
}

