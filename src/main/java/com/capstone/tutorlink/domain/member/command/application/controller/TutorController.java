package com.capstone.tutorlink.domain.member.command.application.controller;

import com.capstone.tutorlink.domain.member.command.application.dto.MemberDTO;
import com.capstone.tutorlink.domain.member.command.application.service.AuthenticationService;
import com.capstone.tutorlink.domain.member.command.application.service.MemberService;
import com.capstone.tutorlink.domain.member.command.domain.aggregate.Member;
import com.capstone.tutorlink.domain.member.command.domain.repository.AcceptedTypeCategoryRepository;
import com.capstone.tutorlink.domain.member.command.domain.repository.UniversityRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
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

    @ApiOperation(value = "튜터 목록 조회", notes = "튜터 목록을 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "튜터 목록 조회 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청")
    })
   @GetMapping("/find_tutor")
   public String findAllTutor(
           @PageableDefault Pageable pageable,
           @RequestParam(name = "memberGender", required = false) String memberGender,
           @RequestParam(name = "tutorUni", required = false) String tutorUni,
           @RequestParam(name = "myKey", required = false) String myKey,
           Model model
   ) {
       Page<MemberDTO> tutorPage = memberService.findAllTutor(pageable, memberGender, tutorUni, myKey);
       model.addAttribute("tutorPage", tutorPage);
       return "member/find_tutor";
   }
    @ApiOperation(value = "튜터 상세 정보 조회", notes = "튜터의 상세 정보를 조회합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "튜터 상세 정보 조회 성공"),
            @ApiResponse(code = 400, message = "잘못된 요청"),
            @ApiResponse(code = 404, message = "튜터를 찾을 수 없음")
    })
    @GetMapping("/tutorDetail/{memberNo}")
    public String getTutorDetail(@PathVariable int memberNo, Model model) {
        // 튜터 상세 정보 조회 로직을 구현하고, model에 필요한 데이터를 추가
        Member tutor = memberService.getTutorByMemberNo(memberNo);
        model.addAttribute("tutor", tutor);
        return "member/tutorDetail";
    }



}
