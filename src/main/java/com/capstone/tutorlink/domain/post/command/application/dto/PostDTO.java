package com.capstone.tutorlink.domain.post.command.application.dto;

import com.capstone.tutorlink.domain.member.command.domain.aggregate.Member;
import com.capstone.tutorlink.domain.post.command.domain.aggregate.BoardCategory;
import lombok.*;
import org.attoparser.dom.Text;
import org.hibernate.annotations.Check;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDTO {

    private Long postNum;

    @NotNull(message = "아이디는 반드시 입력되어야 합니다.")
    private Member postWriter;

    @NotNull(message = "카테고리는 반드시 입력되다어야 합니다.")
    private BoardCategory category;

    @NotNull(message = "글 제목은 반드시 입력되어야 합니다.")
    private String postTitle;

    @NotNull(message = "글 내용은 반드시 입력되어야 합니다.")
    private String postContent;

    @NotNull(message = "글 등록일은 반드시 입력되어야 합니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime postRegistedDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime postUpdatedDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime postDeletedDate;

    @NotNull(message = "게시글 삭제 여부는 반드시 입력되어야 합니다.")
    @Check(constraints = "postIsDeleted IN ('Y', 'N')")
    @Builder.Default
    private Character postIsDeleted = 'N';

    @NotNull(message = "조회수는 반드시 입력되어야 합니다.")
    @Builder.Default
    private Integer postCount = 0;

    @NotNull(message = "게시글 신고 누적 횟수는 반드시 입력되어야 합니다.")
    @Builder.Default
    private Integer postReportedCount = 0;

    @NotNull(message = "게시글 상태는 반드시 입력되어야 합니다.")
    @Check(constraints = "postStatus IN ('A', 'U')")
    @Builder.Default
    private Character postStatus = 'A';

    public String getPostWriterName() {
        return postWriter.getMemberName();
    }

    public String getCategoryCode() {
        return category.getCategoryCode();
    }

    public String getCategoryName() {
        return category.getCategoryName();
    }
}
