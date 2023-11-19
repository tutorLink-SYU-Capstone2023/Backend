package com.capstone.tutorlink.domain.member.command.application.controller;

import com.capstone.tutorlink.domain.member.command.application.dto.MemberDTO;
import com.capstone.tutorlink.domain.member.command.application.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
public class TuteeController {
    private final MemberService memberService;

    public TuteeController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/find_tutee")
    public String findAllTutee(@PageableDefault Pageable pageable, Model model) {
        Page<MemberDTO> tuteePage = memberService.findAllTutee(pageable);
        model.addAttribute("tuteePage", tuteePage);
        return "member/find_tutee";
    }
}
