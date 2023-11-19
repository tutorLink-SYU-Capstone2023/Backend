package com.capstone.tutorlink.domain.member.command.domain.aggregate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORITY")
@Getter
@Setter
public class Authority {
    @Id
    @Column(name = "authority_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorityNum;

    @Column(name = "authority_name")
    private String authorityName;

    @Column(name = "authority_desc")
    private String authorityDesc;
}
