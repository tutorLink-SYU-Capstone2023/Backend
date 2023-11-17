package com.capstone.tutorlink.domain.post.command.application.service;

import com.capstone.tutorlink.domain.member.command.domain.repository.MemberRepository;
import com.capstone.tutorlink.domain.post.command.application.dto.BoardCategoryDTO;
import com.capstone.tutorlink.domain.post.command.application.dto.PostDTO;
import com.capstone.tutorlink.domain.post.command.domain.aggregate.BoardCategory;
import com.capstone.tutorlink.domain.post.command.domain.aggregate.Post;
import com.capstone.tutorlink.domain.post.command.domain.repositoroy.BoardCategoryRepository;
import com.capstone.tutorlink.domain.post.command.domain.repositoroy.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PostService {
    private final BoardCategoryRepository boardCategoryRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    Logger logger = LoggerFactory.getLogger(getClass());
    public PostService(MemberRepository memberRepository, BoardCategoryRepository boardCategoryRepository, PostRepository postRepository, ModelMapper modelMapper){
        this.boardCategoryRepository = boardCategoryRepository;
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    //게시글 리스트, post_num기준으로 내림차순 정렬(최신순 조회)
    @Transactional
    public List<PostDTO> getAllPost(){
        List<Post> postList = postRepository.findAll(Sort.by("postNum").descending());
//        List<Post> postList = postRepository.findAll();
        return postList.stream().map(post -> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
    }

    //신규 게시글 추가
    @Transactional
    public void registNewPost(PostDTO newPost) {
        postRepository.save(modelMapper.map(newPost, Post.class));
    }

    //카테고리 조회(글 등록 시 필요)
    public List<BoardCategoryDTO> findAllCategory() {
        List<BoardCategory> categoryList = boardCategoryRepository.findAll();
        return categoryList.stream().map(boardCategory -> modelMapper.map(boardCategory, BoardCategoryDTO.class)).collect(Collectors.toList());
    }
}
