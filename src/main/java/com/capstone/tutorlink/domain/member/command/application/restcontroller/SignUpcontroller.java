package com.capstone.tutorlink.domain.member.command.application.restcontroller;


import com.capstone.tutorlink.domain.member.command.application.dto.TuteeApiDTO;
import com.capstone.tutorlink.domain.member.command.application.dto.TutorApiDTO;
import com.capstone.tutorlink.domain.member.command.application.service.SignUpService;
//import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/members")
public class SignUpcontroller {

    private final SignUpService signUpService;
//    @ApiOperation(value = "튜티 회원가입", notes="튜티 회원가입 진행")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup_as_tutee")
    public ResponseEntity<String> joinMember(@RequestBody TuteeApiDTO tuteeApiDTO) {
        return signUpService.joinMember(tuteeApiDTO);
    }

    //    @ApiOperation(value = "튜터 회원가입", notes="튜터 회원가입 진행")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup_as_tutor")
    public ResponseEntity<String> join2Member(@RequestBody TutorApiDTO tutorApiDTO) {
        return signUpService.join2Member(tutorApiDTO);
    }
}
