package com.capstone.tutorlink.domain.member.command.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/error")
public class AuthErrorController {
    private final MessageSourceAccessor messageSourceAccessor;
    @Autowired
    public AuthErrorController(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }
    @PostMapping("/signin")
    public String loginFailed(RedirectAttributes rttr) {
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("error.login"));
        return "redirect:/member/signin";
    }

    @GetMapping("/denied")
    public String accessDenied(RedirectAttributes rttr) {
        rttr.addFlashAttribute("message", messageSourceAccessor.getMessage("error.denied"));
        return "redirect:/";
    }

}

