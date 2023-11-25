package com.capstone.tutorlink.domain.main.query.controller;

import com.capstone.tutorlink.domain.member.command.application.dto.MemberDTO;
import com.capstone.tutorlink.domain.member.command.application.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {
    private final MemberService memberService;

    public MainController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping(value = { "/", "/main"})
    public String main(Model model) {
        // 상위 3명의 튜터 정보를 가져와서 모델에 추가
        List<MemberDTO> topTutors = memberService.getTop3LikedTutors();

        model.addAttribute("topTutors", topTutors);

        return "main/main";
    }

    @PostMapping("/")
    public String redirectMain() {

        return "redirect:/";
    }


}
