package com.fastcampus.web.controller.dto;

import com.fastcampus.biz.domain.Blog;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BlogSaveRequestDto {
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }
    public Blog toEntity(){
        return Blog.builder()
                .title(title)
                .status("운영")
                .tag("No tag")
                .build();
    }
}
