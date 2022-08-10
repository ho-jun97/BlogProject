package com.fastcampus.web.controller;

import com.fastcampus.auth.PrincipalDetails;
import com.fastcampus.biz.domain.User;
import com.fastcampus.biz.service.BlogService;
import com.fastcampus.biz.service.CategoryService;
import com.fastcampus.web.controller.dto.CategoryListResponseDto;
import com.fastcampus.web.controller.dto.CategoryResponseDto;
import com.fastcampus.web.controller.dto.CategorySaveRequestDto;
import com.fastcampus.web.controller.dto.CategoryUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class CategoryController {

    private final CategoryService categoryService;
    private final BlogService blogService;

    @GetMapping("/category/list/{id}")
    public String getCategoryList(@PathVariable("id") Long blogId, Model model){
        List<CategoryListResponseDto> categoryList = categoryService.getCategoryList(blogId);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("blog", blogService.findById(blogId));
        return "/category/getCategoryList";
    }

    @PostMapping("/category/save")
    public String save(CategorySaveRequestDto requestDto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        User findUser = principalDetails.getUser();
        categoryService.save(findUser.getUsername(), requestDto);
        return "redirect:/category/list/"+findUser.getBlog().getId();
    }

    @GetMapping("/category/delete/{id}")
    public String delete(@PathVariable("id") Long categoryId,@AuthenticationPrincipal PrincipalDetails principalDetails){
        User findUser = principalDetails.getUser();
        categoryService.delete(categoryId);
        return "redirect:/category/list/"+findUser.getBlog().getId();
    }

    @GetMapping("/category/update/{id}")
    public String updateForm(@PathVariable("id") Long categoryId,Model model){
        CategoryResponseDto categoryDto = categoryService.findById(categoryId);
        List<CategoryListResponseDto> categoryList = categoryService.getCategoryList(categoryDto.getBlog().getId());
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("blog", blogService.findById(categoryDto.getBlog().getId()));
        model.addAttribute("categoryUpdate", true);
        model.addAttribute("category", categoryDto);
        return "/category/getCategoryList";
    }
    @PostMapping("/category/update/{id}")
    public String update(@PathVariable("id") Long categoryId,CategoryUpdateRequestDto requestDto) {
        categoryService.update(categoryId, requestDto);
        CategoryResponseDto categoryDto = categoryService.findById(categoryId);
        return "redirect:/category/list/"+ categoryDto.getBlog().getId();
    }
}
