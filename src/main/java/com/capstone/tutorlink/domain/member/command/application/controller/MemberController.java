package com.capstone.tutorlink.domain.member.command.application.controller;

import com.capstone.tutorlink.domain.member.command.application.dto.MemberDTO;
import com.capstone.tutorlink.domain.member.command.application.service.MemberService;
import com.capstone.tutorlink.global.valid.ErrorResponse;
import com.capstone.tutorlink.global.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public MemberController(MemberService memberService, MessageSourceAccessor messageSourceAccessor) {
        this.memberService = memberService;
        this.messageSourceAccessor = messageSourceAccessor;
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
    public String joinMember(@ModelAttribute MemberDTO memberInfo, RedirectAttributes rttr) {

        log.info("[MemberController] joinMember ==============================");

        // Replace dashes in memberPhoneNumber
        memberInfo.setMemberPhoneNumber(memberInfo.getMemberPhoneNumber().replace("-", ""));

        log.info("[MemberController] joinMember request Member : " + memberInfo);

        memberService.joinMember(memberInfo);

        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.regist"));

        log.info("[MemberController] joinMember ==============================");

        return "redirect:/";
    }


    /*
    @PostMapping("/join")
    public String joinMember(@ModelAttribute MemberDTO memberInfo, RedirectAttributes rttr) {
        memberService.joinMember(memberInfo);
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("member.join"));
        return "redirect:/";
    }
     */

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



    @GetMapping("/mypage")
    public void mypage(@AuthenticationPrincipal MemberDTO member) {
        log.info("로그인 member 번호 : {}", member.getMemberNo());
        log.info("로그인 member 아이디 : {}", member.getMemberId());
        log.info("로그인 member 이름 : {}", member.getMemberName());
    }
    @GetMapping("/tutee")
    public String findAllTutee(@PageableDefault Pageable pageable, Model model){
       return "/member/tutee";
    }
    @GetMapping("/tutor")
    public String findAllTutor(@PageableDefault Pageable pageable, Model model){
        return "/member/tutor";
    }
    @GetMapping("/member/{memberNo}")
    public ResponseEntity<?> findUserByNo() throws UserNotFoundException {
        boolean check = true;
        if(check) {
            throw new UserNotFoundException("회원 정보를 찾을 수 없습니다.");
        }
        return ResponseEntity.ok().build();
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

}
