package com.capstone.tutorlink.domain.member.command.application.controller;

import com.capstone.tutorlink.domain.member.command.application.dto.MemberDTO;
import com.capstone.tutorlink.domain.member.command.application.event.LikeEvent;
import com.capstone.tutorlink.domain.member.command.application.service.LikeNotificationService;
import com.capstone.tutorlink.domain.member.command.application.service.MemberService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
public class LikeController {

    private final MemberService memberService;
    private final ApplicationEventPublisher eventPublisher;

    public LikeController(MemberService memberService, LikeNotificationService likeNotificationService, ApplicationEventPublisher eventPublisher) {
        this.memberService = memberService;
        this.eventPublisher = eventPublisher;
    }

    // 좋아요 폼에서 memberNo를 전달받는 컨트롤러 메서드
    @PostMapping("/member/like")
    public String likeMember(@RequestParam int likedMemberId, @AuthenticationPrincipal MemberDTO member) {
        // 좋아요 로직 구현
        String resultMessage = memberService.likeMember(member.getMemberNo(), likedMemberId);

        // 메시지에 따라 적절한 처리를 수행
        if ("Already liked this member!".equals(resultMessage)) {
            // 이미 좋아요한 경우에 대한 처리
            // 예를 들어, alert 창을 띄우거나 특정 페이지로 리다이렉트
            // return "redirect:/alreadyLikedPage";
        } else {
            // 좋아요 성공의 경우에 대한 처리
            // return "redirect:/tutorDetail/" + likedMemberId;
        }
        return "redirect:/member/tutorDetail/" + likedMemberId; // 좋아요 후 다시 튜터 상세 페이지로 이동하도록 설정
    }

    // 좋아요 취소 폼에서 memberNo를 전달받는 컨트롤러 메서드
    @PostMapping("/member/unlike")
    public String unlikeMember(@RequestParam int likedMemberId, @AuthenticationPrincipal MemberDTO member) {
        // 좋아요 취소 로직 구현
        memberService.unlikeMember(member.getMemberNo(), likedMemberId);

        return "redirect:/member/tutorDetail/" + likedMemberId; // 좋아요 취소 후 다시 튜터 상세 페이지로 이동하도록 설정
    }

    // 좋아요 처리를 위한 컨트롤러 메서드
    @PostMapping("/like")
    public ResponseEntity<String> likeMemberAjax(@RequestParam int likedMemberId, @AuthenticationPrincipal MemberDTO member) {
        String resultMessage = memberService.likeMember(member.getMemberNo(), likedMemberId);

        // 좋아요 이벤트 발생
        if ("Liked successfully".equals(resultMessage)) {
            // LikeEvent를 발행
            eventPublisher.publishEvent(new LikeEvent(this, member.getMemberNo(), likedMemberId));
        }

        return ResponseEntity.ok(resultMessage);
    }


    // 좋아요 취소 처리를 위한 컨트롤러 메서드
    @PostMapping("/unlike")
    public ResponseEntity<String> unlikeMemberAjax(@RequestParam int likedMemberId, @AuthenticationPrincipal MemberDTO member) {
        memberService.unlikeMember(member.getMemberNo(), likedMemberId);
        return ResponseEntity.ok("Unliked successfully");
    }
}
