package com.capstone.tutorlink.domain.member.command.application.event;

import org.springframework.context.ApplicationEvent;

public class LikeEvent extends ApplicationEvent {

    private final int memberId;
    private final int likedMemberId;

    public LikeEvent(Object source, int memberId, int likedMemberId) {
        super(source);
        this.memberId = memberId;
        this.likedMemberId = likedMemberId;
    }

    public int getMemberId() {
        return memberId;
    }

    public int getLikedMemberId() {
        return likedMemberId;
    }
}
