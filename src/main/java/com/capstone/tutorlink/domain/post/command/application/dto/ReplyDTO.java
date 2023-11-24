package com.capstone.tutorlink.domain.post.command.application.dto;

import com.capstone.tutorlink.domain.member.command.application.dto.MemberDTO;
import com.capstone.tutorlink.domain.post.command.domain.aggregate.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReplyDTO {

    @NotNull(message = "댓글 번호는 반드시 입력되어야 합니다.")
    private Long replyNum;

    @NotNull(message = "댓글이 참조하는 게시글은 반드시 입력되어야 합니다.")
    private Long refBoardNum;

    @NotNull(message = "댓글 내용은 입력되어야 합니다.")
    private String replyContent;

    @NotNull(message = "댓글 작성자는 반드시 입력되어야 합니다.")
    private MemberDTO writer;

    @NotNull(message = "댓글 상태는 반드시 입력되어야 합니다.")
    @Check(constraints = "reply_status IN('DELETED', 'REPORTED', 'REGISTED')")
    @Enumerated(value = EnumType.STRING)
    private String replyStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "댓글 작성일은 반드시 입력되어야 합니다.")
    private LocalDateTime createdDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deletedDateTime;
}
