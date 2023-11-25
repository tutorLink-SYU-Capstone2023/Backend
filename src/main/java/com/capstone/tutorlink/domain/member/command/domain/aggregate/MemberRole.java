package com.capstone.tutorlink.domain.member.command.domain.aggregate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "MEMBER_ROLE")
@Getter
@Setter
public class MemberRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_role_code")
    private int memberRoleCode;

    @ManyToOne
    @JoinColumn(name = "authority_num", referencedColumnName = "authority_num")
    private Authority authority;

    @ManyToOne
    @JoinColumn(name = "member_no")
    private Member member;

    public MemberRole() {
    }

    public MemberRole(Authority authority) {
        this.authority = authority;
    }
}