package com.capstone.tutorlink.domain.member.command.application.controller;

import com.capstone.tutorlink.domain.member.command.application.dto.MemberDTO;
import com.capstone.tutorlink.domain.member.command.application.service.AuthenticationService;
import com.capstone.tutorlink.domain.member.command.application.service.MemberService;
import com.capstone.tutorlink.domain.member.command.domain.aggregate.Member;
import com.capstone.tutorlink.domain.member.command.domain.repository.AcceptedTypeCategoryRepository;
import com.capstone.tutorlink.domain.member.command.domain.repository.UniversityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/member")
public class TutorController {
    private final MemberService memberService;
    private final AuthenticationService authenticationService;
    private AcceptedTypeCategoryRepository acceptedTypeCategoryRepository;
    private UniversityRepository universityRepository;

    public TutorController(MemberService memberService, AuthenticationService authenticationService, AcceptedTypeCategoryRepository acceptedTypeCategoryRepository, UniversityRepository universityRepository) {
        this.memberService = memberService;
        this.authenticationService = authenticationService;
        this.acceptedTypeCategoryRepository = acceptedTypeCategoryRepository;
        this.universityRepository = universityRepository;
    }


   /* @GetMapping("/tutor")
    public String findAllTutor(@PageableDefault Pageable pageable, Model model){
        Page<MemberDTO> tutorPage = memberService.findAllTutor(pageable);
        model.addAttribute("tutorPage", tutorPage);
        return "member/tutor";
    }
    */
   @GetMapping("/tutor")
   public String findAllTutor(
           @PageableDefault Pageable pageable,
           @RequestParam(name = "memberGender", required = false) String memberGender,
           @RequestParam(name = "tutorUni", required = false) String tutorUni,
           @RequestParam(name = "myKey", required = false) String myKey,
           Model model
   ) {
       Page<MemberDTO> tutorPage = memberService.findAllTutor(pageable, memberGender, tutorUni, myKey);
       model.addAttribute("tutorPage", tutorPage);
       return "member/tutor";
   }
    @GetMapping("/tutorDetail/{memberNo}")
    public String getTutorDetail(@PathVariable int memberNo, Model model) {
        // 튜터 상세 정보 조회 로직을 구현하고, model에 필요한 데이터를 추가
        Member tutor = memberService.getTutorByMemberNo(memberNo);
        model.addAttribute("tutor", tutor);
        return "member/tutorDetail";
    }



}
