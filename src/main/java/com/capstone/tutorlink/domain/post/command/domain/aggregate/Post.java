package com.capstone.tutorlink.domain.post.command.domain.aggregate;

import com.capstone.tutorlink.domain.member.command.domain.aggregate.Member;
import lombok.Getter;
import lombok.Setter;
import org.attoparser.dom.Text;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "POST")
public class Post {
    @Id
    @Column(name = "post_num")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postNum;

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
    private LocalDateTime postRegistdDate;

    @Column(name = "post_update_date")
    private LocalDateTime postUpdatedDate;

    @Column(name = "post_delete_date")
    private LocalDateTime postDeletedDate;

    @Column(name = "post_is_deleted")
    private Character postIsDeleted;

    @Column(name = "post_count")
    private Integer postCount;

    @Column(name = "post_reported_count")
    private Integer postReportedCount;

    @Column(name = "post_status")
    private Character postStatus;
}
