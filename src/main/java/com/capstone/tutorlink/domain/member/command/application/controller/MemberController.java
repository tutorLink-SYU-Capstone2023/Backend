package com.capstone.tutorlink.domain.member.command.application.controller;

import com.capstone.tutorlink.domain.member.command.application.dto.MemberDTO;
import com.capstone.tutorlink.domain.member.command.application.service.AuthenticationService;
import com.capstone.tutorlink.domain.member.command.application.service.MemberService;
import com.capstone.tutorlink.domain.member.command.domain.aggregate.AcceptedTypeCategory;
import com.capstone.tutorlink.domain.member.command.domain.aggregate.Member;
import com.capstone.tutorlink.domain.member.command.domain.repository.AcceptedTypeCategoryRepository;
import com.capstone.tutorlink.global.valid.ErrorResponse;
import com.capstone.tutorlink.global.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final MessageSourceAccessor messageSourceAccessor;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;
    private AcceptedTypeCategoryRepository acceptedTypeCategoryRepository;

    public MemberController(MemberService memberService, MessageSourceAccessor messageSourceAccessor, PasswordEncoder passwordEncoder, AuthenticationService authenticationService, AcceptedTypeCategoryRepository acceptedTypeCategoryRepository) {
        this.memberService = memberService;
        this.messageSourceAccessor = messageSourceAccessor;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
        this.acceptedTypeCategoryRepository= acceptedTypeCategoryRepository;

    }
    @GetMapping("/login")
    public void loginPage() {}

    @PostMapping("/loginfail")
    public String loginFailed(RedirectAttributes rttr) {

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("error.login"));

        return "redirect:/member/login";
    }

    @GetMapping("/join")
    public void joinPage(){ }

    @PostMapping("/join")
    public String joinMember(@ModelAttribute MemberDTO member, RedirectAttributes rttr) {
        log.info("[MemberController] joinMember ==============================");

        // 사용자가 선택한 field 값
        String selectedField = member.getSelectedField();

        // AcceptedTypeCategory를 참조하여 myKey 설정
        AcceptedTypeCategory acceptedTypeCategory = acceptedTypeCategoryRepository.findByField(selectedField);

        if (acceptedTypeCategory == null) {
            // 사용자가 선택한 field 값이 유효하지 않은 경우에 대한 처리
            rttr.addFlashAttribute("error", "Invalid selected field: " + selectedField);
        } else {
            // AcceptedTypeCategory에서 가져온 myKey를 MemberDTO에 설정
            member.setMyKey(acceptedTypeCategory.getMyKey());

            // 회원 가입을 시도
            try {
                memberService.joinMember(member);
                rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.join"));
            } catch (Exception e) {
                // 회원 가입 실패 시 예외 처리
                rttr.addFlashAttribute("error", "회원 가입에 실패했습니다.");
            }
        }

        log.info("[MemberController] joinMember ==============================");

        return "redirect:/";
    }
    @GetMapping("/join2")
    public void join2Page(){ }

    @PostMapping("/join2")
    public String join2Member(@ModelAttribute MemberDTO member, RedirectAttributes rttr) {
        log.info("[MemberController] join2Member ==============================");

        // 사용자가 선택한 field 값
        String selectedField = member.getSelectedField();

        // AcceptedTypeCategory를 참조하여 myKey 설정
        AcceptedTypeCategory acceptedTypeCategory = acceptedTypeCategoryRepository.findByField(selectedField);

        if (acceptedTypeCategory == null) {
            // 사용자가 선택한 field 값이 유효하지 않은 경우에 대한 처리
            rttr.addFlashAttribute("error", "Invalid selected field: " + selectedField);
        } else {
            // AcceptedTypeCategory에서 가져온 myKey를 MemberDTO에 설정
            member.setMyKey(acceptedTypeCategory.getMyKey());

            // 회원 가입을 시도
            try {
                memberService.join2Member(member);
                rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.join"));
            } catch (Exception e) {
                // 회원 가입 실패 시 예외 처리
                rttr.addFlashAttribute("error", "회원 가입에 실패했습니다.");
            }
        }

        log.info("[MemberController] join2Member ==============================");

        return "redirect:/";
    }


    @PostMapping("/idDupCheck")
    public ResponseEntity<String> checkDuplication(@RequestBody MemberDTO member, RedirectAttributes rttr) {

        log.info("[MemberController] checkDuplication ========================== ");

        log.info("[MemberController] Request Check ID : {}", member.getMemberId());

        if (memberService.selectMemberById(member.getMemberId())) {
            log.info("[MemberController] Already Exist");

            rttr.addFlashAttribute("message", "중복된 아이디입니다."); // 중복된 경우 메시지를 추가
        } else {
            rttr.addFlashAttribute("message", "사용 가능한 아이디입니다."); // 중복되지 않은 경우 메시지를 추가
        }

        log.info("[MemberController] checkDuplication ========================== ");

        return ResponseEntity.ok().build();
    }
    @GetMapping("/update")
    public String goModifyMember() {

        return "member/update";
    }
    @PostMapping("/update")
    public String modifyMember(@ModelAttribute MemberDTO updateMember,
                               @AuthenticationPrincipal MemberDTO loginMember,
                               RedirectAttributes rttr) {
        log.info("[MemberController] modifyMember ==============================");
        log.info("매개변수로 넘어온 멤버", loginMember);

        // 업데이트된 정보를 loginMember에 반영
        loginMember.setMemberNickname(updateMember.getMemberNickname());
        loginMember.setMemberEmail(updateMember.getMemberEmail());
        loginMember.setMemberBirthday(updateMember.getMemberBirthday());
        loginMember.setMemberPhoneNumber(updateMember.getMemberPhoneNumber().replace("-", ""));
        loginMember.setMemberGender(updateMember.getMemberGender()); // 수정된 성별을 반영
        // updateMember.setMyKey(updateMember.getMyKey()); // 필요하다면 처리

        log.info("수정 후 멤버", loginMember);
        log.info("[MemberController] modifyMember request Member : {}", updateMember);

        // memberService를 통해 회원 정보 업데이트
        memberService.modifyMember(updateMember, loginMember);

        // 변경된 회원 정보로 Authentication을 업데이트

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.modify"));

        log.info("[MemberController] modifyMember ==============================");

        return "redirect:/member/mypage";
    }


    @GetMapping("/mypage")
    public void mypage(@AuthenticationPrincipal MemberDTO member) {
        log.info("로그인 member 번호 : {}", member.getMemberNo());
        log.info("로그인 member 아이디 : {}", member.getMemberId());
        log.info("로그인 member 이름 : {}", member.getMemberName());
    }
    @GetMapping("/tutee")
    public String findAllTutee(@PageableDefault Pageable pageable, Model model) {
        Page<MemberDTO> tuteePage = memberService.findAllTutee(pageable);
        model.addAttribute("tuteePage", tuteePage);
        return "member/tutee";
    }


    @GetMapping("/tutor")
    public String findAllTutor(@PageableDefault Pageable pageable, Model model){
        Page<MemberDTO> tutorPage = memberService.findAllTutor(pageable);
        model.addAttribute("tutorPage", tutorPage);
        return "member/tutor";
    }
    @GetMapping("/member/{memberNo}")
    public ResponseEntity<?> findUserByNo() throws UserNotFoundException {
        boolean check = true;
        if(check) {
            throw new UserNotFoundException("회원 정보를 찾을 수 없습니다.");
        }
        return ResponseEntity.ok().build();
    }
    @GetMapping("/member/update")
    public String getUpdatePage(Authentication authentication) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            System.out.println("User has authority: " + authority.getAuthority());
        }
        return "member/update";
    }

    @ControllerAdvice
    public class ExceptionController {
        @ExceptionHandler(UserNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleUserRegistException(UserNotFoundException e) {
            String code = "ERROR_CODE_00000";
            String description = "멤버 정보 조회 실패";
            String detail = e.getMessage();
            return new ResponseEntity<>(new ErrorResponse(code, description, detail), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/leave")
    public String leaveMember(@AuthenticationPrincipal MemberDTO member, RedirectAttributes rttr) {

        log.info("[MemberController] leaveMember ==========================================================");
        log.info("[MemberController] member : " + member);

        memberService.removeMember(member);

        SecurityContextHolder.clearContext();

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.leave"));

        log.info("[MemberController] leaveMember ==========================================================");

        return "redirect:/";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException e) {
        String code = "";
        String description = "";
        String detail = "";
        /* 에러가 있다면 */
        if(e.getBindingResult().hasErrors()) {
            detail = e.getBindingResult().getFieldError().getDefaultMessage();
            String bindResultCode = e.getBindingResult().getFieldError().getCode();
            System.out.println(bindResultCode);
            switch(bindResultCode) {
                case "NotNull" :
                    code = "ERROR_CODE_00001";
                    description = "필수 값이 누락되었습니다.";
                case "NotBlank" :
                    code = "ERROR_CODE_00002";
                    description = "필수 값이 공백으로 처리되었습니다.";
                case "Size" :
                    code = "ERROR_CODE_00003";
                    description = "알맞은 크기의 값이 입력되지 않았습니다.";
            }
        }
        ErrorResponse errorResponse = new ErrorResponse(code, description, detail);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    protected Authentication createNewAuthentication(Authentication currentAuth, String memberId) {

        UserDetails newPrincipal = authenticationService.loadUserByUsername(memberId);
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, currentAuth.getCredentials(), newPrincipal.getAuthorities());
        newAuth.setDetails(currentAuth.getDetails());
        return newAuth;

    }

}
