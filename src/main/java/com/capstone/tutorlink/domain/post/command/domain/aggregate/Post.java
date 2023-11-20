package com.capstone.tutorlink.domain.post.command.domain.aggregate;

import com.capstone.tutorlink.domain.member.command.domain.aggregate.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "post_registd_date")
    private LocalDateTime postRegistedDate;

    @Column(name = "post_updated_date")
    private LocalDateTime postUpdatedDate;

    @Column(name = "post_deleted_date")
    private LocalDateTime postDeletedDate;

    @Column(name = "post_is_deleted")
    private Character postIsDeleted;

    @Column(name = "post_count")
    private Integer postCount;

    @Column(name = "post_reported_count")
    private Integer postReportedCount;

    @Column(name = "post_status")
    private Character postStatus;

    public String getPostWriterName() {
        return postWriter.getMemberName();
    }

    public String getCategoryCode() {
        return category.getCategoryCode();
    }

    public String getCategoryName() {
        return category.getCategoryName();
    }

    public void setCategoryCode(String categoryCode) {
        this.category.setCategoryCode(categoryCode);
    }
}
