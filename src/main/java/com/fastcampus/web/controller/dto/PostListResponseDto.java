package com.fastcampus.web.controller.dto;

import com.fastcampus.biz.domain.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostListResponseDto {
    private Long id;
    private String title;
    private String content;
    private String modifiedDate;
    private CategoryResponseDto category;

    @Builder
    public PostListResponseDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.modifiedDate = post.getModifiedDate();
        this.category = new CategoryResponseDto(post.getCategory());
    }
}
