package com.fastcampus.web.controller;

import com.fastcampus.auth.PrincipalDetails;
import com.fastcampus.biz.domain.User;
import com.fastcampus.biz.service.BlogService;
import com.fastcampus.biz.service.CategoryService;
import com.fastcampus.biz.service.PostService;
import com.fastcampus.biz.service.UserService;
import com.fastcampus.web.controller.dto.*;
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
public class BlogController {
    private final BlogService blogService;
    private final PostService postService;
    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping("/blog/blogMain/{id}")
    public String blogMain(@PathVariable("id") Long blogId, Model model) {
        BlogResponseDto blogDto = blogService.findById(blogId);
        List<PostListResponseDto> postList = (postService.getPostList("", blogDto.getId())); // 내림차순으로 List 저장장
        model.addAttribute("blog", blogDto);
        model.addAttribute("postList", postList);
        return "/blog/blogMain";
    }
    @GetMapping("/blog/blogMain/category/{id}")
    public String getPostList(@PathVariable("id") Long categoryId, Model model) {
        CategoryResponseDto categoryDto = categoryService.findById(categoryId);

        List<PostListResponseDto> postList = (postService.getPostList(categoryDto.getName(), categoryDto.getBlog().getId())); // 내림차순으로 List 저장장
        model.addAttribute("postList", postList);
        model.addAttribute("blog", categoryDto.getBlog());
        return "/blog/blogMain";
    }

    @GetMapping("/blog/insertBlog")
    public String insertBlogForm() {
        return "blog/insertBlog";
    }

    @PostMapping("/blog/insertBlog")
    public String insertBlog(BlogSaveRequestDto requestDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();
        if(user.getBlog() != null){
            return "redirect:/";
        }
        User resultUser = blogService.insertBlog(user.getUsername(), requestDto);
        principalDetails.setUser(resultUser);
        return "redirect:/blog/blogMain/" + resultUser.getBlog().getId();
    }

    @GetMapping("/blog/getBlog/{id}")
    public String getBlog(@PathVariable("id") Long blogId, Model model) {
        model.addAttribute("blog", blogService.findById(blogId));

        return "blog/getBlog";
    }

    @GetMapping("/blog/delete/{id}")
    public String delete(@PathVariable("id") Long blogId) {
        blogService.delete(blogId);
        return "redirect:/";
    }

    @GetMapping("/blog/changeStatus/{id}")
    public String deleteStatus(@PathVariable("id") Long blogId) {
        blogService.deleteStatus(blogId);

        return "redirect:/auth/logout";
    }

    @PostMapping("/blog/update/{id}")
    public String update(@PathVariable("id") Long blogId, BlogUpdateRequestDto requestDto) {
        blogService.update(blogId, requestDto);
        return "redirect:/blog/blogMain/{id}";
    }

    @GetMapping("/blog/deleteRequest/{id}")
    public String deleteRequest(@PathVariable("id") Long blogId, Model model) {
        model.addAttribute("blogId", blogId);
        return "/blog/deleteRequest";
    }
}
