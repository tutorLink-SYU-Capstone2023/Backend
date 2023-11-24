package com.capstone.tutorlink.domain.post.command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AttachmentDTO {

    private Long attachmentNum;
    private Long refBoardNo;
    private String originalName;
    private String savedName;
    private String savePath;
    private String fileType;
    private String thumbnailPath;
    private String attachmentStatus;
}
