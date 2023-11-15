package com.capstone.tutorlink.domain.post.command.application.controller;

import com.capstone.tutorlink.domain.member.command.application.dto.MemberDTO;
import com.capstone.tutorlink.domain.post.command.application.dto.BoardCategoryDTO;
import com.capstone.tutorlink.domain.post.command.application.dto.PostDTO;
import com.capstone.tutorlink.domain.post.command.application.service.BoardCategoryService;
import com.capstone.tutorlink.domain.post.command.application.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/list")
    public String findAllPost(@PageableDefault Pageable pageable, Model model) {
        Page<PostDTO> postPage = postService.findAllPost(pageable);
        model.addAttribute("postList", postPage);
        return "post/list";

    }


    //게시글 카테고리 생성
    @PostMapping("/regist")
    public String registNewPost(PostDTO newPost){
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
