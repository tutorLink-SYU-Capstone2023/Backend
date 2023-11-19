package com.capstone.tutorlink.domain.member.command.domain.aggregate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "LIKED_MEMBERS")
public class LikedMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_no", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "liked_member_id", referencedColumnName = "member_no", nullable = false)
    private Member likedMember;

    // Constructors, getters, setters, etc.

    public void setMember(Member member) {
        this.member = member;
    }

    public void setLikedMember(Member likedMember) {
        this.likedMember = likedMember;
    }
}
