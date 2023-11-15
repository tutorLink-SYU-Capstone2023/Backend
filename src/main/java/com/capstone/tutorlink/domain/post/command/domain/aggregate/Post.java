package com.capstone.tutorlink.domain.post.command.domain.aggregate;

import com.capstone.tutorlink.domain.member.command.domain.aggregate.Member;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "POST")
public class Post {
    @Id
    @Column(name = "post_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postNum;

    @ManyToOne
    @JoinColumn(referencedColumnName = "member_no", name = "post_writer")
    private Member postWriter;

    @ManyToOne
    @JoinColumn(referencedColumnName = "category_code", name = "category_code")
    private BoardCategory category;


    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_content")
    private String postContent;

    @Column(name = "post_regist_date")
    private LocalDateTime postRegistDate;

    @Column(name = "post_update_date")
    private LocalDateTime postUpdateDate;
    @Column(name = "post_delete_date")
    private LocalDateTime postDeleteDate;
    @Column(name = "post_is_deleted")
    private char postIsDeleted;
    @Column(name = "post_count")
    private int postCount;
    @Column(name = "post_reported_count")
    private int postReportedCount;
}
