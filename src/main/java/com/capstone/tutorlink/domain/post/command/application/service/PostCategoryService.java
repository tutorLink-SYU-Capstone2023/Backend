package com.capstone.tutorlink.domain.post.command.application.service;

import com.capstone.tutorlink.domain.post.command.application.dto.PostCategoryDTO;
import com.capstone.tutorlink.domain.post.command.domain.aggregate.PostCategory;
import com.capstone.tutorlink.domain.post.command.domain.repositoroy.PostCategoryRepositoroy;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class PostCategoryService {
    private PostCategoryRepositoroy postCategoryRepositoroy;
    private ModelMapper modelMapper;
    public PostCategoryService(PostCategoryRepositoroy postCategoryRepositoroy, ModelMapper modelMapper) {
        this.postCategoryRepositoroy = postCategoryRepositoroy;
        this.modelMapper = modelMapper;
    }

    //신규 게시글 카테고리 등록 처리
    @Transactional
    public void createNewPostCategory(PostCategoryDTO newPostCategory) {
        postCategoryRepositoroy.save(modelMapper.map(newPostCategory, PostCategory.class));
    }

    //기존 게시글 카테고리 수정 처리
    @Transactional
    public void updatePostCategory(PostCategoryDTO postCategory){
        postCategoryRepositoroy.flush();
    }

    //기존 게시글 카테고리 삭제 처리
}
