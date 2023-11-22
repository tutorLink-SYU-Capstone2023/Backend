package com.capstone.tutorlink.domain.post.command.application.controller;

import com.capstone.tutorlink.domain.post.command.application.dto.BoardCategoryDTO;
import com.capstone.tutorlink.domain.post.command.application.dto.PostDTO;
import com.capstone.tutorlink.domain.post.command.application.service.PostService;
import com.capstone.tutorlink.domain.post.command.domain.aggregate.Post;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    //전체 게시글 조회
//    @GetMapping("/all")
//    public String findAllPost(@PageableDefault Pageable pageable, Model model) {
//        List<PostDTO> postList = postService.getAllPost();
//        model.addAttribute("postList", postList);
//        return "post/all";
//
//    }

//    //게시글 상세
//    @GetMapping({"/detail/{postNum}"})
//    @ResponseBody
//    public ResponseEntity<PostDTO> findByNum(@PathVariable Long postNum, Model model){
//        PostDTO post = postService.findPostByNum(postNum);
//        if(post != null){
//            return new ResponseEntity<>(post, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    //게시글 등록 페이지로 이동
//    @GetMapping("/regist")
//    public void registNewPost() {}
//
//    //게시글 등록 정보 전달
//
//
//    //글 등록 시 카테고리 조회
//    @GetMapping(value = "/category", produces = "application/json; charset+UTF-8")
//    @ResponseBody
//    public List<BoardCategoryDTO> findCategoryList(){
//        return postService.findAllCategory();
//    }
//
//    //게시글 수정 페이지로 이동
//    @GetMapping("/update")
//    public void updatePage() {}
//
//    //게시글 수정
//    @PostMapping("/update")
//    public String updatePost(PostDTO updatePost){
//        postService.updatePost(updatePost);
//        return "redirect:/post/detail"+updatePost.getPostNum();
//    }
//
//    //게시글 삭제
//    @GetMapping("/delete")
//    public void deletePost(){}
//
//    @PostMapping("/delete/{postNum}")
//    public String deletePost(@PathVariable Long postNum) {
//        postService.deletePost(postNum);
//        return "redirect:/post/all";
//    }
//




    //게시글 조회
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllPosts(@PageableDefault Pageable pageable, PagedResourcesAssembler<Post> assembler) {
        Page<Post> posts = postService.findAll(pageable);
        PagedModel<EntityModel<Post>> model = assembler.toModel(posts);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }

    //게시글 등록
    @PostMapping("/regist")
    public ResponseEntity<?> registNewPost(@RequestBody Post post) {
        Post newPost = postService.save(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @PutMapping("/{postNum}")
    public ResponseEntity<?> putPost(@PathVariable("postNum") Long postNum, @RequestBody Post post){
        Post persistPost = postService.findPostByNum(postNum);
        persistPost.update(post);
        Post savedPost = postService.save(persistPost);
        return new ResponseEntity<>(savedPost, HttpStatus.OK);
    }

    @DeleteMapping("/{postNum}")
    public ResponseEntity<?> deletePost(@PathVariable("postNum") Long postNum) {
        postService.deleteByNum(postNum);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
