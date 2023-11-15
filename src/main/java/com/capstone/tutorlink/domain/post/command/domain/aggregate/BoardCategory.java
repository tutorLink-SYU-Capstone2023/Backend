package com.capstone.tutorlink.domain.post.command.domain.aggregate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "BOARD_CATEGORY")

public class BoardCategory {

    @Id
    @OneToMany(mappedBy = "category")
    private enum categoryCode

    @Column(name = "category_name")
    private String categoryName;


}
