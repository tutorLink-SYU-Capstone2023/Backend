package com.capstone.tutorlink.domain.post.command.application.controller;

import com.capstone.tutorlink.domain.post.command.application.dto.BoardCategoryDTO;
import com.capstone.tutorlink.domain.post.command.application.service.BoardCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board/category")
public class BoardCategoryController {

    private final BoardCategoryService boardCategoryService;

    public BoardCategoryController(BoardCategoryService boardCategoryService) {
        this.boardCategoryService = boardCategoryService;
    }

    //게시글 카테고리 생성
    @GetMapping(value = "/create")
    public String createNewPostCategory(BoardCategoryDTO newPostCategory) {
        boardCategoryService.createNewPostCategory(newPostCategory);
        return "/category/createPostCategory";
    }
    //게시글 카테고리 수정

    @GetMapping(value = "/update")
    public String updatePostCategory(BoardCategoryDTO postCategory){
        boardCategoryService.updatePostCategory(postCategory);
        return "/category/updatePostCategory";
    }
    //게시글 카테고리 삭제
}
