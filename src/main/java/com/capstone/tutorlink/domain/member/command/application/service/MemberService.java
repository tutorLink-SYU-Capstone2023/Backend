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

import javax.persistence.EntityNotFoundException;
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


    @Transactional
    public Member modifyMember(MemberDTO updateMember, MemberDTO loginMember) {
        try {
            // 데이터베이스에서 현재 로그인한 사용자 정보를 가져옵니다.
            log.info("MemberNo :{}" ,loginMember.getMemberNo()) ;
            Member savedMember = memberRepository.findByMemberNo(loginMember.getMemberNo());
            System.out.println(savedMember.toString());
            System.out.println("---------------------------");
            if (savedMember != null) {
                // 변경된 정보를 업데이트합니다.
                log.info("0") ;
                //loginMember.getMemberNickname()
                savedMember.setMemberNickname(updateMember.getMemberNickname());
                log.info("1") ;
                //loginMember.getMemberEmail()
                savedMember.setMemberEmail(updateMember.getMemberEmail());
                log.info("2") ;
                //loginMember.getMemberGender()
                savedMember.setMemberGender(updateMember.getMemberGender());
                log.info("3") ;
                //loginMember.getMemberBirthday()
                savedMember.setMemberBirthday(updateMember.getMemberBirthday());
                //loginMember.getMemberPhoneNumber()
                savedMember.setMemberPhoneNumber(updateMember.getMemberPhoneNumber().replace("-", ""));
                log.info("5") ;

                // 변경사항을 저장
                memberRepository.save(savedMember);

                return savedMember;
            } else {
                // 사용자를 찾을 수 없음
                throw new EntityNotFoundException("User not found with ID: " + loginMember.getMemberNo());
            }
        } catch (Exception e) {
            // 예외 처리 - 예외 메시지를 로그에 기록
            log.error("Error in modifyMember: " + e.getMessage());
            throw e; // 예외 다시 던지기
        }
    }


    public void removeMember(MemberDTO member) {

        Member savedMember = memberRepository.findByMemberNo(member.getMemberNo());
        savedMember.setMemberCurrentStatus("D");

    }
}