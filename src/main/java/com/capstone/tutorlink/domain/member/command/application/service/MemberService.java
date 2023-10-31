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
import java.util.Date;

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

        return memberRepository.findByMemberIdAndMemberCurrentStatus(memberId, "A").isPresent();
    }

    /*
    @Transactional
    public void joinMember(MemberDTO memberInfo) {
        Member member = modelMapper.map(memberInfo, Member.class);
        member.setMemberId(memberInfo.getMemberId());
        member.setMemberPw(passwordEncoder.encode(memberInfo.getMemberPw()));
        member.setMemberNickname(memberInfo.getMemberNickname());
        member.setMemberName(memberInfo.getMemberName());
        member.setMemberEmail(memberInfo.getMemberEmail());
        member.setMemberGender(memberInfo.getMemberGender());
        member.setMemberBirthday(memberInfo.getMemberBirthday());
        member.setMemberEnrollDate(new Date());
        member.setMemberCurrentStatus("A");
        member.setMemberPhoneNumber(memberInfo.getMemberPhoneNumber());
        member.setMyKey(memberInfo.getMyKey());
        // 권한 설정
        Authority authority = authorityRepository.findByAuthorityName("ROLE_TUTEE");
        member.setMemberRoleList(Collections.singletonList(new MemberRole(authority)));
        member.setMemberPw(passwordEncoder.encode(memberInfo.getMemberPw()));

         // 회원 엔티티에 권한 설정
        member.setMemberRoleList(Collections.singletonList(memberRole));

        // 회원 저장
        memberRepository.save(member);
    }
    */
    @Transactional
    public void joinMember(MemberDTO memberInfo) {
        // 회원 엔티티 생성
        Member member = new Member();
        member.setMemberId(memberInfo.getMemberId());
        member.setMemberPw(passwordEncoder.encode(memberInfo.getMemberPw()));
        member.setMemberNickname(memberInfo.getMemberNickname());
        member.setMemberName(memberInfo.getMemberName());
        member.setMemberEmail(memberInfo.getMemberEmail());
        member.setMemberGender(memberInfo.getMemberGender());
        member.setMemberBirthday(memberInfo.getMemberBirthday());
        member.setMemberPhoneNumber(memberInfo.getMemberPhoneNumber());
        member.setMyKey(memberInfo.getMyKey());

        // 권한 설정
        Authority authority = authorityRepository.findByAuthorityName("ROLE_TUTEE");
        MemberRole memberRole = new MemberRole(authority);

        // 회원 저장
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
        savedMember.setMemberCurrentStatus("D");

    }
}