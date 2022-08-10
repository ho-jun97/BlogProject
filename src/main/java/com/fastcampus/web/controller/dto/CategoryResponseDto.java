package com.fastcampus.web.controller.dto;

import com.fastcampus.biz.domain.Category;
import com.fastcampus.biz.domain.DisplayType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String description;
    private DisplayType displayType;
    private BlogResponseDto blog;

    public CategoryResponseDto(Category category){
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.displayType = category.getDisplayType();
        this.blog = new BlogResponseDto(category.getBlog());
    }
}
