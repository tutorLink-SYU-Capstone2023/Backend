package com.capstone.tutorlink.domain.member.command.domain.aggregate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_member")
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no")
    private int memberNo;
    @Column(name = "member_id")
    private String memberId;
    @Column(name = "member_pwd")
    private String memberPwd;
    @Column(name = "member_name")
    private String memberName;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "member_no")
    public List<MemberRole> memberRoleList;
}
