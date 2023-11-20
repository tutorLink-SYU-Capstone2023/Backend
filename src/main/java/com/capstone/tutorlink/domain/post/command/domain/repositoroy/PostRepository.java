package com.capstone.tutorlink.domain.post.command.domain.repositoroy;

import com.capstone.tutorlink.domain.post.command.domain.aggregate.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Override
    List<Post> findAll();
    void deletePostByPostNum(Long postNum);
}
