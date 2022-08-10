package com.fastcampus.web.controller.dto;

import com.fastcampus.biz.domain.Category;
import com.fastcampus.biz.domain.DisplayType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CategoryListResponseDto {
    private Long id;
    private String name;
    private String description;
    private DisplayType displayType;

    @Builder
    public CategoryListResponseDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.displayType = category.getDisplayType();

    }
}
