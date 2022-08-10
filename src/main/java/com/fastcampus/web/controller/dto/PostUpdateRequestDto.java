package com.fastcampus.web.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostUpdateRequestDto {
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
}
