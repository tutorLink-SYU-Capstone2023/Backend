package com.capstone.tutorlink.domain.member.command.application.service;

import com.capstone.tutorlink.domain.member.command.application.dto.MemberDTO;
import com.capstone.tutorlink.domain.member.command.domain.aggregate.Authority;
import com.capstone.tutorlink.domain.member.command.domain.aggregate.Member;
import com.capstone.tutorlink.domain.member.command.domain.aggregate.MemberRole;
import com.capstone.tutorlink.domain.member.command.domain.repository.AuthorityRepository;
import com.capstone.tutorlink.domain.member.command.domain.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

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
    public boolean selectMemberById(String memberId) {

        return memberRepository.findByMemberIdAndMemberStatus(memberId, "Y").isPresent();
    }

    @Transactional
    public void joinMember(MemberDTO memberInfo) {
        Member member = modelMapper.map(memberInfo, Member.class);
        Authority authority = authorityRepository.findByAuthorityName("ROLE_TUTEE");
        member.setMemberRoleList(Collections.singletonList(new MemberRole(authority)));
        member.setMemberPw(passwordEncoder.encode(memberInfo.getMemberPw()));
        memberRepository.save(member);
    }

    public void registMember(MemberDTO member) {

        memberRepository.save(modelMapper.map(member, Member.class));
    }

    public void modifyMember(MemberDTO updateMember) {

        Member savedMember = memberRepository.findByMemberNo((long) updateMember.getMemberNo());
        savedMember.setMemberNickname(updateMember.getMemberNickname());
        savedMember.setMemberPhoneNumber(updateMember.getMemberPhoneNumber());
        savedMember.setMemberEmail(updateMember.getMemberEmail());
        savedMember.setMemberGender(updateMember.getMemberGender());

    }

    public void removeMember(MemberDTO member) {

        Member savedMember = memberRepository.findByMemberNo((long) member.getMemberNo());
        savedMember.setMemberStatus("N");

    }
}