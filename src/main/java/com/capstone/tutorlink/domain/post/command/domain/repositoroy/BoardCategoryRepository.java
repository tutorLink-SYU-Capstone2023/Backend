package com.capstone.tutorlink.domain.post.command.domain.repositoroy;

import com.capstone.tutorlink.domain.post.command.domain.aggregate.BoardCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardCategoryRepository extends JpaRepository<BoardCategory, String> {
    @Query(value =
            "SELECT category_code, category_name " +
            "FROM BOARD_CATEGORY "+
            "ORDER BY category_code ASC", nativeQuery = true)
    //Query method를 검색해서 활용해보기( 더 좋은 방식이다.)
    public List<BoardCategory> findAllCategory();
}
