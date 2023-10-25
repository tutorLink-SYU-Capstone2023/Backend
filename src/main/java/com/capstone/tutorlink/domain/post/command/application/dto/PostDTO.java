package com.capstone.tutorlink.domain.post.command.application.dto;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private int postNum;
    private int postWriter;
    private String category;
    private String postTitle;
    private String postContent;
    private Date postRegistDate;
    private Date postUpdateDate;
    private Date postDeleteDate;
    private char postIsDeleted;
    private int postCount;
    private int postReportedCount;
}
