package com.fastcampus.biz.persistence;

import com.fastcampus.biz.domain.Category;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c from Category c WHERE c.blog.id = :blogId ORDER BY c.id DESC ")
    List<Category> findAllDesc(@Param("blogId") Long blogId);

    @Query("SELECT c FROM Category c WHERE c.name = :name AND c.blog.id = :blogId")
    Optional<Category> findByName(@Param("name") String name, @Param("blogId") Long blogId);
}
