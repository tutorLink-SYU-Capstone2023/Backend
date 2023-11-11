package com.capstone.tutorlink.domain.member.command.domain.aggregate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "UNIVERSITY")
@Getter
@Setter
@NoArgsConstructor
public class University {

    @Id
    @Column(name = "univ_code")
    private String univCode;

    @Column(name = "univ_name")
    private String univName;

    @Column(name = "category_code")
    private String categoryCode;

    public University(String univCode, String univName, String categoryCode) {
        this.univCode = univCode;
        this.univName = univName;
        this.categoryCode = categoryCode;
    }
}
