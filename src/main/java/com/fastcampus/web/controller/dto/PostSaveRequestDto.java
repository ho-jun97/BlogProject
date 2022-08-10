package com.fastcampus.web.controller.dto;

import com.fastcampus.biz.domain.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostSaveRequestDto {
    private String title;
    private String content;
    private String categoryName;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .build();
    }
}
