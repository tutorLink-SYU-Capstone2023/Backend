package com.capstone.tutorlink.domain.post.command.domain.aggregate;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "POST_CATEGORY")
public class PostCategory {

    @Id
    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "category_name")
    private String categoryName;


}
