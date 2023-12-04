package com.capstone.tutorlink.domain.member.command.application.controller;

import com.capstone.tutorlink.domain.member.command.application.dto.LoginDTO;
import com.capstone.tutorlink.domain.member.command.application.service.LoginService;
import com.capstone.tutorlink.global.dto.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {

    private final LoginService loginService;

    @PostMapping("/signin")
    //반환값 ResponseEntity<?> 로 수정
    //사유 : 반환값의 자료형 다름의 차이
    public TokenInfo signIn(@RequestBody LoginDTO LoginDTO) {
        /*
        ID Pw 검증하여 true , flase 반환
        TRue 면 return loginService.login(LoginDTO.getUsername(), LoginDTO.getPassword());
        flase면 "ID 또는 PW 가 다릅니다 로 반환
         */
        System.out.println(LoginDTO.getUsername());

        return loginService.login(LoginDTO.getUsername(), LoginDTO.getPassword());
    }
}
