package com.capstone.tutorlink.domain.member.command.domain.aggregate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "liked_members")
public class LikedMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "liked_member_id")
    private Integer likedMemberId;


    // Constructors, getters, setters, etc.
    // LikedMember 엔티티

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public void setLikedMemberId(int likedMemberId) {
        this.likedMemberId = likedMemberId;
    }


}
