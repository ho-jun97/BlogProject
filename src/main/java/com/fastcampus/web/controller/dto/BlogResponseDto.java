package com.fastcampus.web.controller.dto;

import com.fastcampus.biz.domain.Blog;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class BlogResponseDto {
    private Long id;
    private String status;
    private String tag;
    private String title;
    private List<CategoryListResponseDto> categoryList;


    public BlogResponseDto(Blog blog){
        this.id = blog.getId();
        this.status = blog.getStatus();
        this.tag = blog.getTag();
        this.title = blog.getTitle();
        this.categoryList = blog.getCategoryList().stream().map(CategoryListResponseDto::new).collect(Collectors.toList());
    }
}
