package com.capstone.tutorlink.domain.post.command.domain.aggregate;

import com.capstone.tutorlink.domain.member.command.domain.aggregate.Member;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter @Setter
@Entity
@DynamicInsert
@Table(name = "REPLY")
public class Reply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPLY_NO")
    private Long replyNum;

    private Long refPostNum;

    private String replyContent;

    @ManyToOne
    @JoinColumn(name = "")
    private Member writer;

    private String replyStatus;

    private LocalDateTime registedDateTime;

    private LocalDateTime updatedDateTime;

    private LocalDateTime deletedDateTime;




}
