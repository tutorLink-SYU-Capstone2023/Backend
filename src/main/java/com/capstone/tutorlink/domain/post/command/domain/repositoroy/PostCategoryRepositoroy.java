package com.capstone.tutorlink.domain.post.command.domain.repositoroy;

import com.capstone.tutorlink.domain.post.command.domain.aggregate.PostCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCategoryRepositoroy extends JpaRepository<PostCategory, String> {
}
