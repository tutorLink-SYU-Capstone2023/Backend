package com.capstone.tutorlink.domain.post.command.domain.aggregate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TBL_ATTACHMENT")
@DynamicInsert
public class Attachment {

    @Id
    @Column(name = "ATTACHMENT_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attachmentNo;

    @Column(name = "REF_BOARD_NO")
    private Long refBoardNo;

    @Column(name = "ORIGINAL_NAME")
    private String originalName;

    @Column(name = "SAVED_NAME")
    private String savedName;

    @Column(name = "SAVE_PATH")
    private String savePath;

    @Column(name = "FILE_TYPE")
    private String fileType;

    @Column(name = "THUMBNAIL_PATH")
    private String thumbnailPath;

    @Column(name = "ATTACHMENT_STATUS")
    private String attachmentStatus;

}