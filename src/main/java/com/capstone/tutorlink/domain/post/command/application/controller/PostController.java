package com.capstone.tutorlink.domain.post.command.application.controller;

import com.capstone.tutorlink.domain.member.command.domain.aggregate.Member;
import com.capstone.tutorlink.domain.post.command.application.dto.BoardCategoryDTO;
import com.capstone.tutorlink.domain.post.command.application.dto.PostDTO;
import com.capstone.tutorlink.domain.post.command.application.service.BoardCategoryService;
import com.capstone.tutorlink.domain.post.command.application.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService){
        this.postService = postService;
    }

    //게시글 카테고리 생성
    @PostMapping("/regist")
    public String registNewPost(Member member, PostDTO newPost){
        postService.registNewPost(newPost);
        return "redirect:/post/list";
    }
    //게시글 카테고리 수정
    //게시글 카테고리 삭제

    @GetMapping(value = "/category", produces = "application/json; charset+UTF-8")
    @ResponseBody
    public List<BoardCategoryDTO> findCategoryList(){
        return postService.findAllCategory();
    }
}
