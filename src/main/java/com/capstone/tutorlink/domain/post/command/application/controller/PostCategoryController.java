package com.capstone.tutorlink.domain.post.command.application.controller;

import com.capstone.tutorlink.domain.post.command.application.dto.PostCategoryDTO;
import com.capstone.tutorlink.domain.post.command.application.service.PostCategoryService;
import com.capstone.tutorlink.domain.post.command.domain.aggregate.PostCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/post-category")
public class PostCategoryController {

    private final PostCategoryService postCategoryService;

    public PostCategoryController(PostCategoryService postCategoryService) {
        this.postCategoryService = postCategoryService;
    }

    //게시글 카테고리 생성
    @GetMapping(value = "/create")
    public String createNewPostCategory(PostCategoryDTO newPostCategory) {
        postCategoryService.createNewPostCategory(newPostCategory);
        return "/category/createPostCategory";
    }
    //게시글 카테고리 수정

    @GetMapping(value = "/update")
    public String updatePostCategory(PostCategoryDTO postCategory){
        postCategoryService.updatePostCategory(postCategory);
        return "/category/updatePostCategory";
    }
    //게시글 카테고리 삭제
}
