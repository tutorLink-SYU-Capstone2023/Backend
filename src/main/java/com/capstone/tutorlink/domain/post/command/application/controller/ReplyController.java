package com.capstone.tutorlink.domain.post.command.application.controller;

import com.capstone.tutorlink.domain.member.command.application.dto.MemberDTO;
import com.capstone.tutorlink.domain.post.command.application.dto.ReplyDTO;
import com.capstone.tutorlink.domain.post.command.application.service.PostService;
import com.capstone.tutorlink.global.error.ErrorCode;
import com.capstone.tutorlink.global.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestController
@RequestMapping("/api/{postNum}")
public class ReplyController {
    private final PostService postService;
    private final MessageSourceAccessor messageSourceAccesor;

    public ReplyController(PostService postService, MessageSourceAccessor messageSourceAccesor) {
        this.postService = postService;
        this.messageSourceAccesor = messageSourceAccesor;
    }

    @PostMapping("/regist-reply")
    public ResponseEntity<String> registReply(@RequestBody ReplyDTO registReply, @AuthenticationPrincipal MemberDTO member){
        try{
            registReply.setWriter(member);
//            postService.registReply(registReply);
            return ResponseEntity.ok("댓글 등록 완료");
        } catch(Exception e){
            throw new RestApiException(ErrorCode.BAD_REQUEST);

        } finally {

        }

    }
}
