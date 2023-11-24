package com.capstone.tutorlink.domain.post.command.domain.repositoroy;

import com.capstone.tutorlink.domain.post.command.domain.aggregate.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Override
    List<Reply> findAll();

    void deleteReplyByReplyNum(Long replyNum);
}
