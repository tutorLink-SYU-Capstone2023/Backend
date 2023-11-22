package com.capstone.tutorlink.domain.member.command.application.service;

import com.capstone.tutorlink.domain.member.command.application.dto.MemberDTO;
import com.capstone.tutorlink.domain.member.command.application.event.LikeEvent;
import com.capstone.tutorlink.domain.member.command.domain.aggregate.*;
import com.capstone.tutorlink.domain.member.command.domain.repository.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Slf4j
@Service
@Api(tags = "회원 서비스", description = "회원과 관련된 비즈니스 로직을 처리하는 서비스입니다.")
public class MemberService {
    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AcceptedTypeCategoryRepository acceptedTypeCategoryRepository;
    private final UniversityRepository universityRepository;
    private final LikedMemberRepository likedMemberRepository;
    private final ApplicationEventPublisher eventPublisher;
    public MemberService(MemberRepository memberRepository, AuthorityRepository authorityRepository,
                         ModelMapper modelMapper, PasswordEncoder passwordEncoder, AcceptedTypeCategoryRepository acceptedTypeCategoryRepository, UniversityRepository universityRepository, LikedMemberRepository likedMemberRepository, ApplicationEventPublisher eventPublisher) {
        this.memberRepository = memberRepository;
        this.authorityRepository = authorityRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.acceptedTypeCategoryRepository= acceptedTypeCategoryRepository;
        this.universityRepository = universityRepository;
        this.likedMemberRepository = likedMemberRepository;
        this.eventPublisher = eventPublisher;
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
    public Page<MemberDTO> findAllTutor(org.springframework.data.domain.Pageable pageable, String memberGender, String tutorUni, String myKey) {
        Page<Member> tutorPage = memberRepository.findAllTutorWithConditions(memberGender, tutorUni, myKey, pageable);
        return tutorPage.map(member -> modelMapper.map(member, MemberDTO.class));
    }

    @ApiOperation(value = "튜티 회원 가입")
    @ApiResponses({
            @ApiResponse(code = 200, message = "튜티 회원 가입 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
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

    @ApiOperation(value = "튜터 회원 가입")
    @ApiResponses({
            @ApiResponse(code = 200, message = "튜터 회원 가입 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
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

            // MemberRole을 MemberEntity의 MemberRole 목록에 추가
            memberEntity.getMemberRoleList().add(memberRole);

            // 사용자가 선택한 field 값
            String selectedField = member.getSelectedField();
            String selectedUnivName = member.getSelectedUnivName();

            // AcceptedTypeCategory를 참조하여 myKey 설정
            AcceptedTypeCategory acceptedTypeCategory = acceptedTypeCategoryRepository.findByField(selectedField);
            University university = universityRepository.findByUnivName(selectedUnivName);

            if (acceptedTypeCategory != null) {
                // AcceptedTypeCategory에서 myKey 값을 가져와서 memberEntity의 myKey에 설정
                memberEntity.setMyKey(acceptedTypeCategory.getMyKey());
                memberEntity.setTutorUni(university.getUnivCode());

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


    @ApiOperation(value = "회원 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 수정 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청"),
            @ApiResponse(code = 404, message = "회원을 찾을 수 없음")
    })
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

    @ApiOperation(value = "회원 탈퇴")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 탈퇴 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
    @Transactional
    public void removeMember(MemberDTO member) {

        Member savedMember = memberRepository.findByMemberNo(member.getMemberNo());
        savedMember.setMemberCurrentStatus("D");

    }
    @Transactional
    public Member getTutorByMemberNo(int memberNo) {
        return memberRepository.findByMemberNo(memberNo);
    }

    // 다른 회원을 좋아요 하는 메서드
    @Transactional
    public String likeMember(int memberId, int likedMemberId) {
        Member member = memberRepository.findByMemberNo(memberId);
        Member likedMember = memberRepository.findByMemberNo(likedMemberId);

        // 이미 좋아요한 경우 중복 좋아요를 방지하기 위해 확인
        if (member.getLikedMembers().contains(likedMember)) {
            return "Already liked this member!";
        }

        member.getLikedMembers().add(likedMember);
        likedMember.getLikedByMembers().add(member);

        // 좋아요 이벤트 발생
        eventPublisher.publishEvent(new LikeEvent(this, memberId, likedMemberId));

        return "Liked successfully!";
    }

    @Transactional
    public void unlikeMember(int memberId, int likedMemberId) {
        likedMemberRepository.deleteByMember_MemberNoAndLikedMember_MemberNo(memberId, likedMemberId);
    }

}