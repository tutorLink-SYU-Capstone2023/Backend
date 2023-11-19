package com.capstone.tutorlink.domain.member.command.application.controller;

import com.capstone.tutorlink.domain.member.command.domain.aggregate.LikeNotification;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LikeNotificationController {

    @MessageMapping("/likeNotification")  // 클라이언트에서 메시지를 보낼 때의 엔드포인트
    @SendTo("/topic/likeNotifications")  // 메시지를 구독하는 클라이언트에게 전송할 주제(prefix)
    public LikeNotification sendLikeNotification(LikeNotification likeNotification) {
        // 실제로는 좋아요 알림을 처리하고, 새로운 알림을 생성하거나 데이터베이스에 저장합니다.
        return likeNotification;
    }
}
