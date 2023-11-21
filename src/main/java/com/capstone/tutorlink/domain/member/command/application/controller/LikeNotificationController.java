package com.capstone.tutorlink.domain.member.command.application.controller;

import com.capstone.tutorlink.domain.member.command.domain.aggregate.LikeNotification;
import com.capstone.tutorlink.domain.member.command.application.service.LikeNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/member")
public class LikeNotificationController {

    private final LikeNotificationService likeNotificationService;

    public LikeNotificationController(LikeNotificationService likeNotificationService) {
        this.likeNotificationService = likeNotificationService;
    }

    @GetMapping("/notification")
    public void notificationPage() {}

    @MessageMapping("/likeNotification")
    @SendTo("/topic/likeNotifications")
    public LikeNotification sendLikeNotification(LikeNotification likeNotification) {
        // 실제로는 좋아요 알림을 처리하고, 새로운 알림을 생성하거나 데이터베이스에 저장합니다.
        return likeNotificationService.processLikeNotification(likeNotification);
    }
}
