package com.fastcampus.web.controller.dto;

import com.fastcampus.biz.domain.Blog;
import com.fastcampus.biz.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BlogListResponseDto {
    private Long id;
    private String title;
    private String status;
    private User user;

    @Builder
    public BlogListResponseDto(Blog blog) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.status = blog.getStatus();
        this.user = blog.getUser();
    }
}
