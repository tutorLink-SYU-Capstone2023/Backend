package com.capstone.tutorlink.domain.post.command.application.controller;

import com.capstone.tutorlink.domain.post.command.application.dto.BoardCategoryDTO;
import com.capstone.tutorlink.domain.post.command.application.dto.PostDTO;
import com.capstone.tutorlink.domain.post.command.application.service.PostService;
import com.capstone.tutorlink.domain.post.command.domain.aggregate.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    //게시글 조회
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllPosts(@PageableDefault Pageable pageable, PagedResourcesAssembler<Post> assembler) {

        log.info("[PostController] getAllPosts enter ======================================");
        Page<Post> posts = postService.findAll(pageable);
        PagedModel<EntityModel<Post>> model = assembler.toModel(posts);
        log.info("[PostController] getAllPosts return ======================================");
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    //게시글 상세 조회
    @PutMapping("/{postNum}")
    public ResponseEntity<?> putPost(@PathVariable("postNum") Long postNum, @RequestBody Post post){
        Post persistPost = postService.findPostByNum(postNum);
        persistPost.update(post);
        Post savedPost = postService.save(persistPost);
        return new ResponseEntity<>(savedPost, HttpStatus.OK);
    }

    //게시글 등록
    @PostMapping("/regist")
    public ResponseEntity<?> registNewPost(@RequestBody Post post) {
        Post newPost = postService.save(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    //게시글 삭제
    @DeleteMapping("/{postNum}")
    public ResponseEntity<?> deletePost(@PathVariable("postNum") Long postNum) {
        postService.deleteByNum(postNum);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
