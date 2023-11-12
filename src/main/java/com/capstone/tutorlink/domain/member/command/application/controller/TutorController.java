package com.capstone.tutorlink.domain.member.command.application.controller;

import com.capstone.tutorlink.domain.member.command.application.dto.MemberDTO;
import com.capstone.tutorlink.domain.member.command.application.service.MemberService;
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

    public TutorController(MemberService memberService) {
        this.memberService = memberService;
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


}
