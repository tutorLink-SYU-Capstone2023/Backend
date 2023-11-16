package com.capstone.tutorlink.domain.post.command.application.dto;

import com.capstone.tutorlink.domain.member.command.domain.aggregate.Member;
import com.capstone.tutorlink.domain.post.command.domain.aggregate.BoardCategory;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private String postNum;
    private Member postWriter;
    private BoardCategory category;
    private String postTitle;
    private String postContent;
    private LocalDateTime postRegistDate;
    private LocalDateTime postUpdateDate;
    private LocalDateTime postDeleteDate;
    private char postIsDeleted;
    private int postCount;
    private int postReportedCount;
}
