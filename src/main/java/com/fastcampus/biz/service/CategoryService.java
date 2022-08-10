package com.fastcampus.biz.service;

import com.fastcampus.biz.domain.Blog;
import com.fastcampus.biz.domain.Category;
import com.fastcampus.biz.domain.User;
import com.fastcampus.biz.persistence.CategoryRepository;
import com.fastcampus.web.controller.dto.CategoryListResponseDto;
import com.fastcampus.web.controller.dto.CategoryResponseDto;
import com.fastcampus.web.controller.dto.CategorySaveRequestDto;
import com.fastcampus.web.controller.dto.CategoryUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserService userService;

    @Transactional
    public void save(String username, CategorySaveRequestDto requestDto){
        if(requestDto.getName().equals("")){ // 카테고리 명이 없으면 저장 x
            return;
        }
        User user = userService.findByUser(username);
        Blog blog = user.getBlog();
        Optional<Category> findCategory = categoryRepository.findByName(requestDto.getName(),blog.getId());
        if(findCategory.isPresent()){ // 이미 등록된 카테고리 명이면 저장 x
            return;
        }
        Category category = requestDto.toEntity();

        blog.addCategory(category);

        categoryRepository.save(category);
    }
    @Transactional(readOnly = true)
    public List<CategoryListResponseDto> getCategoryList(Long blogId){
        List<Category> list = categoryRepository.findAllDesc(blogId);
        return list.stream().map(CategoryListResponseDto::new).collect(Collectors.toList());
    }
    @Transactional
    public void delete(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("해당 카테고리가 없습니다."));
        categoryRepository.delete(category);
    }

    @Transactional
    public void update(Long categoryId, CategoryUpdateRequestDto requestDto){
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                ()-> new IllegalArgumentException("해당 카테고리가 없습니다."));

        category.update(requestDto.getName(), requestDto.getDescription(), requestDto.getDisplayType());
    }

    public CategoryResponseDto findById(Long categoryId){
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                ()-> new IllegalArgumentException("해당 카테고리가 없습니다."));

        return new CategoryResponseDto(category);
    }
}
