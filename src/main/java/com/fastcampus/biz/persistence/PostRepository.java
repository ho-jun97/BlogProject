package com.fastcampus.biz.persistence;

import com.fastcampus.biz.domain.Post;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.category.name = :name AND p.blog.id = :blogId ORDER BY p.id DESC")
    List<Post> findByCategoryDesc(@Param("name") String name, @Param("blogId") Long blogId);

    @Query("SELECT p FROM Post p WHERE p.blog.id = :blogId ORDER BY p.id DESC")
    List<Post> findAllDesc(@Param("blogId") Long blogId);
}
