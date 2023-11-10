package com.capstone.tutorlink.domain.member.command.domain.aggregate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCEPTEDTYPE_CATEGORY")
@Getter
@Setter
public class AcceptedTypeCategory {
    @Id
    @Column(name = "my_key")
    private String myKey;

    @Column(name = "Field")
    private String field;

}
