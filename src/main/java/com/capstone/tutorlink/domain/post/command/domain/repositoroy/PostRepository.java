package com.capstone.tutorlink.domain.post.command.domain.repositoroy;

import com.capstone.tutorlink.domain.post.command.domain.aggregate.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

//    @Query("SELECT p FROM Post p WHERE p.postStatus = 'V'")
    @Query("SELECT p FROM Post p")
    Page<Post> findAllPost(org.springframework.data.domain.Pageable pageable);
}
