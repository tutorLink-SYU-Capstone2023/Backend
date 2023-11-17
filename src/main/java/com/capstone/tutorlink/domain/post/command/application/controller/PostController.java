package com.capstone.tutorlink.domain.post.command.application.controller;

import com.capstone.tutorlink.domain.post.command.application.dto.BoardCategoryDTO;
import com.capstone.tutorlink.domain.post.command.application.dto.PostDTO;
import com.capstone.tutorlink.domain.post.command.application.service.PostService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService){
        this.postService = postService;
    }

    //전체 게시글 조회
    @GetMapping("/all")
    public String findAllPost(@PageableDefault Pageable pageable, Model model) {
        List<PostDTO> postList = postService.getAllPost();
        model.addAttribute("postList", postList);
        return "post/all";

    }

    //게시글 상세
    @GetMapping({"/detail/{postNum}"})
    public String findByCode(@PathVariable Long postNum, Model model){
        PostDTO post = postService.findPostByNum(postNum);
        model.addAttribute("post", post);
        return "post/detail";
    }

    //게시글 등록 페이지로 이동
    @GetMapping("/regist")
    public void registNewPost() {}

    //게시글 등록 정보 전달
    @PostMapping("/regist")
    public String registNewPost(PostDTO newPost){
        postService.registNewPost(newPost);
        return "redirect:/post/all";
    }


    //글 등록 시 카테고리 조회
    @GetMapping(value = "/category", produces = "application/json; charset+UTF-8")
    @ResponseBody
    public List<BoardCategoryDTO> findCategoryList(){
        return postService.findAllCategory();
    }
}
