package com.capstone.tutorlink.domain.member.command.domain.aggregate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "tbl_member_role")
@Getter @Setter
public class MemberRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_role_code")
    private int memberRoleCode;
    @Column(name = "member_no")
    private int memberNo;
    @ManyToOne
    @JoinColumn(name = "authority_code")
    private Authority authority;
    public MemberRole() {}
    public MemberRole(Authority authority) {
        this.authority = authority;
    }
}
