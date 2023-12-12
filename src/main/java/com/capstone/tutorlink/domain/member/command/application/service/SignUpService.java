package com.capstone.tutorlink.domain.member.command.application.service;

import com.capstone.tutorlink.domain.member.command.application.dto.TuteeApiDTO;
import com.capstone.tutorlink.domain.member.command.domain.aggregate.AcceptedTypeCategory;
import com.capstone.tutorlink.domain.member.command.domain.aggregate.Authority;
import com.capstone.tutorlink.domain.member.command.domain.aggregate.Member;
import com.capstone.tutorlink.domain.member.command.domain.aggregate.MemberRole;
import com.capstone.tutorlink.domain.member.command.domain.repository.AcceptedTypeCategoryRepository;
import com.capstone.tutorlink.domain.member.command.domain.repository.AuthorityRepository;
import com.capstone.tutorlink.domain.member.command.domain.repository.MemberRepository;
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
    private final PasswordEncoder passwordEncoder;

    public SignUpService(MemberRepository memberRepository, AuthorityRepository authorityRepository,
                         ModelMapper modelMapper, PasswordEncoder passwordEncoder,
                         AcceptedTypeCategoryRepository acceptedTypeCategoryRepository) {
        this.memberRepository = memberRepository;
        this.authorityRepository = authorityRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.acceptedTypeCategoryRepository = acceptedTypeCategoryRepository;
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

    private void validateSelectedField(String selectedField) {
        AcceptedTypeCategory acceptedTypeCategory = acceptedTypeCategoryRepository.findByField(selectedField);
        if (acceptedTypeCategory == null) {
            throw new ValidationException("Invalid selected field: " + selectedField);
        }
    }

    private String buildAddress(TuteeApiDTO tuteeApiDTO) {
        return tuteeApiDTO.getZipCode() + "$" + tuteeApiDTO.getAddress1() + "$" + tuteeApiDTO.getAddress2();
    }

    private Member convertToMember(TuteeApiDTO tuteeApiDTO) {
        return modelMapper.map(tuteeApiDTO, Member.class);
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
}
