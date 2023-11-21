package com.capstone.tutorlink.domain.member.command.domain.aggregate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class LikeNotification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int memberId;
    private int likedMemberId;

    // 추가 필요한 정보가 있다면 여기에 추가

    public LikeNotification(int memberId, int likedMemberId) {
        this.memberId = memberId;
        this.likedMemberId = likedMemberId;
        // 추가 필요한 정보 초기화
    }
}

