package com.fastcampus.biz.persistence;

import com.fastcampus.biz.domain.Blog;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    @Query("SELECT b FROM Blog b ORDER BY b.id DESC")
    List<Blog> findAllDesc();

    @Query("SELECT b FROM Blog b WHERE b.title LIKE %:title% ORDER BY b.id DESC")
    List<Blog> findByTitleDesc(@Param("title") String title);

    @Query("SELECT b FROM Blog b WHERE b.tag LIKE %:tag% ORDER BY b.id DESC")
    List<Blog> findByTagDesc(@Param("tag") String tag);
}
