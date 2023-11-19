package com.capstone.tutorlink.domain.member.command.domain.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "MEMBER_ROLE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_role_code")
    private int memberRoleCode;

    @Column(name = "authority_num")
    private Integer authorityNum;

    @Column(name = "member_no")
    private Integer memberNo;

    @ManyToOne
    @JoinColumn(name = "authority_num", referencedColumnName = "authority_num", insertable = false, updatable = false)
    private Authority authority;

    @ManyToOne
    @JoinColumn(name = "member_no", insertable = false, updatable = false)
    private Member member;

    public MemberRole(Authority authority) {
        this.authority = authority;
    }
}
