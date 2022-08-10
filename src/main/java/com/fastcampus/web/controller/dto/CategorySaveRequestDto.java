package com.fastcampus.web.controller.dto;

import com.fastcampus.biz.domain.Category;
import com.fastcampus.biz.domain.DisplayType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CategorySaveRequestDto {
    private String name;
    private String description;
    private DisplayType displayType;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDisplayType(DisplayType displayType) {
        this.displayType = displayType;
    }

    public Category toEntity(){
        return Category.builder()
                .name(name)
                .description(description)
                .displayType(displayType)
                .build();
    }
}
