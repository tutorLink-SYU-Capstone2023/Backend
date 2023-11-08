package com.capstone.tutorlink.domain.post.command.application.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Long postNum;
    private Long postWriter;
    private String category;
    private String postTitle;
    private String postContent;
    private LocalDateTime postRegistDate;
    private LocalDateTime postUpdateDate;
    private LocalDateTime postDeleteDate;
    private char postIsDeleted;
    private int postCount;
    private int postReportedCount;
}
