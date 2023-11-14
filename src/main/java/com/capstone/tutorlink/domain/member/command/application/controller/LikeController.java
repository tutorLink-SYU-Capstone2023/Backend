package com.capstone.tutorlink.domain.member.command.application.controller;

import com.capstone.tutorlink.domain.member.command.application.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
public class LikeController {

    private final MemberService memberService;

    public LikeController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 다른 회원을 좋아요 하는 요청 처리
    @PostMapping("/like")
    public ResponseEntity<String> likeMember(@RequestParam int memberId, @RequestParam int likedMemberId) {
        memberService.likeMember(memberId, likedMemberId);
        return ResponseEntity.ok("Liked successfully");
    }

    // 좋아요를 취소하는 요청 처리
    @PostMapping("/unlike")
    public ResponseEntity<String> unlikeMember(@RequestParam int memberId, @RequestParam int likedMemberId) {
        memberService.unlikeMember(memberId, likedMemberId);
        return ResponseEntity.ok("Unliked successfully");
    }
}

