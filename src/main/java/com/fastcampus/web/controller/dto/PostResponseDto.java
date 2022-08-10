package com.fastcampus.web.controller.dto;

import com.fastcampus.biz.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private BlogResponseDto blog;
    private CategoryResponseDto category;

    public PostResponseDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.blog = new BlogResponseDto(post.getBlog());
        this.category = new CategoryResponseDto(post.getCategory());
    }
}
