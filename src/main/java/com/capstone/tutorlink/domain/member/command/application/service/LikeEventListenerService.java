package com.capstone.tutorlink.domain.member.command.application.service;

import com.capstone.tutorlink.domain.member.command.application.event.LikeEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class LikeEventListenerService {

    private final SimpMessagingTemplate messagingTemplate;

    public LikeEventListenerService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleLikeEvent(LikeEvent event) {
        // WebSocket을 통해 알림 전송
        messagingTemplate.convertAndSendToUser(
                String.valueOf(event.getLikedMemberId()),
                "/topic/likeNotifications",
                "You have a new like from member " + event.getMemberId());
    }
}
