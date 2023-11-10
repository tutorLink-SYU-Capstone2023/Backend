package com.capstone.tutorlink.domain.member.command.application.service;

import com.capstone.tutorlink.domain.member.command.application.dto.MemberDTO;
import com.capstone.tutorlink.domain.member.command.domain.aggregate.AcceptedTypeCategory;
import com.capstone.tutorlink.domain.member.command.domain.aggregate.Authority;
import com.capstone.tutorlink.domain.member.command.domain.aggregate.Member;
import com.capstone.tutorlink.domain.member.command.domain.aggregate.MemberRole;
import com.capstone.tutorlink.domain.member.command.domain.repository.AcceptedTypeCategoryRepository;
import com.capstone.tutorlink.domain.member.command.domain.repository.AuthorityRepository;
import com.capstone.tutorlink.domain.member.command.domain.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.Collections;
import java.util.Date;

@Slf4j
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AcceptedTypeCategoryRepository acceptedTypeCategoryRepository;
    public MemberService(MemberRepository memberRepository, AuthorityRepository authorityRepository,
                         ModelMapper modelMapper, PasswordEncoder passwordEncoder, AcceptedTypeCategoryRepository acceptedTypeCategoryRepository) {
        this.memberRepository = memberRepository;
        this.authorityRepository = authorityRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.acceptedTypeCategoryRepository= acceptedTypeCategoryRepository;
    }

    @Transactional
    public boolean selectMemberById(String memberId) {

        return memberRepository.findByMemberIdAndMemberCurrentStatus(memberId, "A").isPresent();
    }
    @Transactional
    public Page<MemberDTO> findAllTutee(org.springframework.data.domain.Pageable pageable) {
        Page<Member> tuteePage = memberRepository.findAllTutee(pageable);
        return tuteePage.map(member -> modelMapper.map(member, MemberDTO.class));
    }
    @Transactional
    public Page<MemberDTO> findAllTutor(org.springframework.data.domain.Pageable pageable) {
        Page<Member> tutorPage = memberRepository.findAllTutor(pageable);
        return tutorPage.map(member -> modelMapper.map(member, MemberDTO.class));
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
            MemberRole memberRole = new MemberRole(authority);
            memberRole.setMember(memberEntity);

            // MemberRole을 MemberEntity의 MemberRole 목록에 추가합니다.
            memberEntity.getMemberRoleList().add(memberRole);

            // 사용자가 선택한 field 값
            String selectedField = member.getSelectedField();

            // AcceptedTypeCategory를 참조하여 myKey 설정
            AcceptedTypeCategory acceptedTypeCategory = acceptedTypeCategoryRepository.findByField(selectedField);

            if (acceptedTypeCategory != null) {
                // AcceptedTypeCategory에서 myKey 값을 가져와서 memberEntity의 myKey에 설정
                memberEntity.setMyKey(acceptedTypeCategory.getMyKey());

                // 회원 저장
                memberRepository.save(memberEntity);
            } else {
                // 사용자가 선택한 field 값이 유효하지 않은 경우 예외 처리
                log.error("Invalid selected field: " + selectedField);
                throw new IllegalArgumentException("Invalid selected field: " + selectedField);
            }
        } catch (Exception e) {
            // 예외 처리 - 예외 메시지를 로그에 기록
            log.error("Error in joinMember: " + e.getMessage());
            throw e; // 예외를 다시 던집니다.
        }
    }
    @Transactional
    public void join2Member(MemberDTO member) {
        try {
            // MemberDTO를 Member로 변환
            Member memberEntity = modelMapper.map(member, Member.class);

            // 비밀번호 암호화
            String encryptedPassword = passwordEncoder.encode(member.getMemberPw());
            memberEntity.setMemberPw(encryptedPassword);

            // 권한 설정
            Authority authority = authorityRepository.findByAuthorityName("ROLE_TUTOR");
            MemberRole memberRole = new MemberRole(authority);
            memberRole.setMember(memberEntity);

            // MemberRole을 MemberEntity의 MemberRole 목록에 추가합니다.
            memberEntity.getMemberRoleList().add(memberRole);

            // 사용자가 선택한 field 값
            String selectedField = member.getSelectedField();

            // AcceptedTypeCategory를 참조하여 myKey 설정
            AcceptedTypeCategory acceptedTypeCategory = acceptedTypeCategoryRepository.findByField(selectedField);

            if (acceptedTypeCategory != null) {
                // AcceptedTypeCategory에서 myKey 값을 가져와서 memberEntity의 myKey에 설정
                memberEntity.setMyKey(acceptedTypeCategory.getMyKey());

                // 회원 저장
                memberRepository.save(memberEntity);
            } else {
                // 사용자가 선택한 field 값이 유효하지 않은 경우 예외 처리
                log.error("Invalid selected field: " + selectedField);
                throw new IllegalArgumentException("Invalid selected field: " + selectedField);
            }
        } catch (Exception e) {
            // 예외 처리 - 예외 메시지를 로그에 기록
            log.error("Error in joinMember: " + e.getMessage());
            throw e; // 예외를 다시 던집니다.
        }
    }


    @Transactional
    public Member modifyMember(MemberDTO updateMember, MemberDTO loginMember) {
        try {
            Member savedMember = memberRepository.findByMemberNo(loginMember.getMemberNo());

            if (savedMember != null) {
                savedMember.setMemberNickname(updateMember.getMemberNickname());
                savedMember.setMemberEmail(updateMember.getMemberEmail());
                savedMember.setMemberGender(loginMember.getMemberGender()); // 성별은 loginMember에서 가져오기
                savedMember.setMemberBirthday(updateMember.getMemberBirthday());
                savedMember.setMemberPhoneNumber(updateMember.getMemberPhoneNumber().replace("-",""));

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


    @Transactional
    public void removeMember(MemberDTO member) {

        Member savedMember = memberRepository.findByMemberNo(member.getMemberNo());
        savedMember.setMemberCurrentStatus("D");

    }
}