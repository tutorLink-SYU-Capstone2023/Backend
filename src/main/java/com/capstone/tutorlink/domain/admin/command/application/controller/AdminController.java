package com.capstone.tutorlink.domain.admin.command.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    //관리자 계정으로 게시판 조회
    @GetMapping("/dashboard")
    public String getDashboard() {
        return "admin/dashboard";
    }
}

