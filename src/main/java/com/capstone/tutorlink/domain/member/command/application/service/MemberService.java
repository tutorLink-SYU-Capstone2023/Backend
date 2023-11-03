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

    @Transactional
    public void joinMember(MemberDTO member) {
        try {
            // MemberDTO를 Member로 변환
            Member memberEntity = modelMapper.map(member, Member.class);

            // 비밀번호 암호화
            String encryptedPassword = passwordEncoder.encode(member.getMemberPw());
            memberEntity.setMemberPw(encryptedPassword);

            // 권한 설정
            Authority authority = authorityRepository.findByAuthorityName("ROLE_TUTEE");
            memberEntity.setMemberRoleList(Collections.singletonList(new MemberRole(authority)));

            // 회원 저장
            memberRepository.save(memberEntity);
        } catch (Exception e) {
            // 예외 처리 - 예외 메시지를 로그에 기록
            log.error("Error in joinMember: " + e.getMessage());
        }
    }




    public void registMember(MemberDTO member) {

        memberRepository.save(modelMapper.map(member, Member.class));
    }

    @Transactional
    public void modifyMember(MemberDTO updateMember) {

        Member savedMember = memberRepository.findByMemberNo((long) updateMember.getMemberNo());
        //savedMember.setMemberPw(updateMember.getMemberPw());
        savedMember.setMemberNickname(updateMember.getMemberNickname());
        //savedMember.setMemberName(updateMember.getMemberName());
        savedMember.setMemberEmail(updateMember.getMemberEmail());
        savedMember.setMemberGender(updateMember.getMemberGender());
        savedMember.setMemberBirthday(updateMember.getMemberBirthday());
        savedMember.setMemberPhoneNumber(updateMember.getMemberPhoneNumber());
        //savedMember.setMyKey(updateMember.getMyKey());

        // 변경사항을 저장
        memberRepository.save(savedMember);

    }

    public void removeMember(MemberDTO member) {

        Member savedMember = memberRepository.findByMemberNo((long) member.getMemberNo());
        savedMember.setMemberCurrentStatus("D");

    }
}