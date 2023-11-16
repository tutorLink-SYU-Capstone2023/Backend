package com.capstone.tutorlink.domain.post.command.application.service;

import com.capstone.tutorlink.domain.post.command.application.dto.BoardCategoryDTO;
import com.capstone.tutorlink.domain.post.command.domain.aggregate.BoardCategory;
import com.capstone.tutorlink.domain.post.command.domain.repositoroy.BoardCategoryRepository;
import com.capstone.tutorlink.domain.post.command.domain.repositoroy.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BoardCategoryService {
    private final BoardCategoryRepository boardCategoryRepository;
    private final ModelMapper modelMapper;
    public BoardCategoryService(BoardCategoryRepository boardCategoryRepository, ModelMapper modelMapper) {
        this.boardCategoryRepository = boardCategoryRepository;
        this.modelMapper = modelMapper;
    }

    //신규 게시글 카테고리 등록 처리
    @Transactional
    public void createNewPostCategory(BoardCategoryDTO newPostCategory) {
        boardCategoryRepository.save(modelMapper.map(newPostCategory, BoardCategory.class));
    }

    //기존 게시글 카테고리 수정 처리
    @Transactional
    public void updatePostCategory(BoardCategoryDTO postCategory){
        boardCategoryRepository.flush();
    }

    //기존 게시글 카테고리 삭제 처리
}
