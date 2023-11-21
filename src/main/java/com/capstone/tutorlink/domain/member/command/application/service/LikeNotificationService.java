package com.capstone.tutorlink.domain.member.command.application.service;

import com.capstone.tutorlink.domain.member.command.domain.aggregate.LikeNotification;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class LikeNotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public LikeNotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public LikeNotification processLikeNotification(LikeNotification likeNotification) {
        // 좋아요 알림을 처리하고, 새로운 알림을 생성하거나 데이터베이스에 저장하는 로직을 추가합니다.

        // 웹소켓을 통해 알림 전송
        messagingTemplate.convertAndSendToUser(
                String.valueOf(likeNotification.getLikedMemberId()),
                "/topic/likeNotifications",
                "You have a new like from member " + likeNotification.getMemberId());

        return likeNotification;
    }

    public void sendLikeNotification(int likedMemberId, String message) {
        messagingTemplate.convertAndSendToUser(
                String.valueOf(likedMemberId),
                "/member/topic/likeNotifications",
                message);
    }

}
