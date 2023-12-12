package com.capstone.tutorlink.domain.member.command.application.service;

import com.capstone.tutorlink.domain.member.command.application.dto.TuteeApiDTO;
import com.capstone.tutorlink.domain.member.command.application.dto.TutorApiDTO;
import com.capstone.tutorlink.domain.member.command.domain.aggregate.*;
import com.capstone.tutorlink.domain.member.command.domain.repository.AcceptedTypeCategoryRepository;
import com.capstone.tutorlink.domain.member.command.domain.repository.AuthorityRepository;
import com.capstone.tutorlink.domain.member.command.domain.repository.MemberRepository;
import com.capstone.tutorlink.domain.member.command.domain.repository.UniversityRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import javax.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class SignUpService {

    private final MemberRepository memberRepository;
    private final AuthorityRepository authorityRepository;
    private final ModelMapper modelMapper;
    private final AcceptedTypeCategoryRepository acceptedTypeCategoryRepository;
    private final UniversityRepository universityRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpService(MemberRepository memberRepository, AuthorityRepository authorityRepository,
                         ModelMapper modelMapper, PasswordEncoder passwordEncoder,
                         AcceptedTypeCategoryRepository acceptedTypeCategoryRepository, UniversityRepository universityRepository) {
        this.memberRepository = memberRepository;
        this.authorityRepository = authorityRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.acceptedTypeCategoryRepository = acceptedTypeCategoryRepository;
        this.universityRepository = universityRepository;
    }

    @Transactional
    public ResponseEntity<String> joinMember(TuteeApiDTO tuteeApiDTO) {
        try {
            // 유효한 선택 필드인지 검증
            validateSelectedField(tuteeApiDTO.getSelectedField());

            // 주소 설정
            tuteeApiDTO.setAddress(buildAddress(tuteeApiDTO));

            // Member로 변환
            Member memberEntity = convertToMember(tuteeApiDTO);

            // 비밀번호 암호화 및 권한 설정
            encryptPasswordAndSetRole(memberEntity, tuteeApiDTO.getSelectedField());

            // 회원 저장
            memberRepository.save(memberEntity);

            return ResponseEntity.ok("회원 가입 성공");
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            log.error("Error in joinMember: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("회원 가입에 실패했습니다. 이미 존재하는 사용자입니다.");
        } catch (Exception e) {
            log.error("Error in joinMember: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 가입에 실패했습니다. 서버 오류가 발생했습니다.");
        }
    }
    @Transactional
    public ResponseEntity<String> join2Member(TutorApiDTO tutorApiDTO) {
        try {
            // 유효한 선택 필드인지 검증
            validateSelectedField(tutorApiDTO.getSelectedField());
            validateSelectedUnivName(tutorApiDTO.getSelectedUnivName());

            // 주소 설정
            tutorApiDTO.setAddress(buildTutorAddress(tutorApiDTO));

            // Member로 변환
            Member memberEntity = convertToTutor(tutorApiDTO);

            // 비밀번호 암호화 및 권한 설정
            encryptPasswordAndSetRole2(memberEntity, tutorApiDTO.getSelectedField(),tutorApiDTO.getSelectedUnivName());

            // 회원 저장
            memberRepository.save(memberEntity);

            return ResponseEntity.ok("튜터 회원 가입 성공");
        } catch (ValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            log.error("Error in joinMember: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("튜터 회원 가입에 실패했습니다. 이미 존재하는 사용자입니다.");
        } catch (Exception e) {
            log.error("Error in joinMember: " + e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("회원 가입에 실패했습니다. 서버 오류가 발생했습니다.");
        }
    }

    private void validateSelectedField(String selectedField) {
        AcceptedTypeCategory acceptedTypeCategory = acceptedTypeCategoryRepository.findByField(selectedField);
        if (acceptedTypeCategory == null) {
            throw new ValidationException("Invalid selected field: " + selectedField);
        }
    }
    private void validateSelectedUnivName(String selectedUnivName) {
        University university = universityRepository.findByUnivName(selectedUnivName);
        if (university == null) {
            throw new ValidationException("Invalid selected UnivName: " + selectedUnivName);
        }
    }

    private String buildAddress(TuteeApiDTO tuteeApiDTO) {
        return tuteeApiDTO.getZipCode() + "$" + tuteeApiDTO.getAddress1() + "$" + tuteeApiDTO.getAddress2();
    }
    private String buildTutorAddress(TutorApiDTO tutorApiDTO) {
        return tutorApiDTO.getZipCode() + "$" + tutorApiDTO.getAddress1() + "$" + tutorApiDTO.getAddress2();
    }

    private Member convertToMember(TuteeApiDTO tuteeApiDTO) {
        return modelMapper.map(tuteeApiDTO, Member.class);
    }

    private Member convertToTutor(TutorApiDTO tutorApiDTO) {
        return modelMapper.map(tutorApiDTO, Member.class);
    }

    private void encryptPasswordAndSetRole(Member memberEntity, String selectedField) {
        String encryptedPassword = passwordEncoder.encode(memberEntity.getMemberPw());
        memberEntity.setMemberPw(encryptedPassword);

        Authority authority = authorityRepository.findByAuthorityName("ROLE_TUTEE");
        MemberRole memberRole = new MemberRole(authority);
        memberRole.setMember(memberEntity);

        memberEntity.getMemberRoleList().add(memberRole);

        AcceptedTypeCategory acceptedTypeCategory = acceptedTypeCategoryRepository.findByField(selectedField);
        memberEntity.setMyKey(acceptedTypeCategory.getMyKey());

        memberEntity.setAddress(memberEntity.getAddress());
    }

    private void encryptPasswordAndSetRole2(Member memberEntity, String selectedField, String selectedUnivName) {
        String encryptedPassword = passwordEncoder.encode(memberEntity.getMemberPw());
        memberEntity.setMemberPw(encryptedPassword);

        Authority authority = authorityRepository.findByAuthorityName("ROLE_TUTOR");
        MemberRole memberRole = new MemberRole(authority);
        memberRole.setMember(memberEntity);

        memberEntity.getMemberRoleList().add(memberRole);

        AcceptedTypeCategory acceptedTypeCategory = acceptedTypeCategoryRepository.findByField(selectedField);
        University university = universityRepository.findByUnivName(selectedUnivName);
        memberEntity.setMyKey(acceptedTypeCategory.getMyKey());
        memberEntity.setTutorUni(university.getUnivCode());

        memberEntity.setAddress(memberEntity.getAddress());
    }



}
