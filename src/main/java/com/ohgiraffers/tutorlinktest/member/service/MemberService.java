package com.ohgiraffers.tutorlinktest.member.service;

import com.ohgiraffers.tutorlinktest.member.dto.MemberDTO;
import com.ohgiraffers.tutorlinktest.member.entity.Authority;
import com.ohgiraffers.tutorlinktest.member.entity.Member;
import com.ohgiraffers.tutorlinktest.member.entity.MemberRole;
import com.ohgiraffers.tutorlinktest.member.respository.AuthorityRepository;
import com.ohgiraffers.tutorlinktest.member.respository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.Collections;

@Slf4j
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    public MemberService(MemberRepository memberRepository, AuthorityRepository authorityRepository,
                         ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.authorityRepository = authorityRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }
    @Transactional
    public void joinMember(MemberDTO memberInfo) {
        Member member = modelMapper.map(memberInfo, Member.class);
        Authority authority = authorityRepository.findByAuthorityName("ROLE_MEMBER");
        member.setMemberRoleList(Collections.singletonList(new MemberRole(authority)));
        member.setMemberPwd(passwordEncoder.encode(memberInfo.getMemberPwd()));
        memberRepository.save(member);
    }

}