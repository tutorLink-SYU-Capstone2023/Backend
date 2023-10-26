package com.capstone.tutorlink.domain.post.command.domain.repositoroy;

import com.capstone.tutorlink.domain.post.command.domain.aggregate.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardCategoryRepository extends JpaRepository<BoardCategory, String> {
    @Query(value = "SELECT tutorlink.BOARD_CATEGORY.category_code, tutorlink.BOARD_CATEGORY.category_name "+"ORDER BY category_code ASC", nativeQuery = true)
    public List<BoardCategory> findAllCategory();
}
