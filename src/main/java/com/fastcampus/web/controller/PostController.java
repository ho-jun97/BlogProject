package com.fastcampus.web.controller;

import com.fastcampus.auth.PrincipalDetails;
import com.fastcampus.biz.domain.User;
import com.fastcampus.biz.service.BlogService;
import com.fastcampus.biz.service.PostService;
import com.fastcampus.biz.service.UserService;
import com.fastcampus.web.controller.dto.BlogResponseDto;
import com.fastcampus.web.controller.dto.PostResponseDto;
import com.fastcampus.web.controller.dto.PostSaveRequestDto;
import com.fastcampus.web.controller.dto.PostUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final BlogService blogService;
    private final PostService postService;
    private final UserService userService;

    @GetMapping("/post/save")
    public String saveForm(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){
        User findUser = userService.findByUser(principalDetails.getUser().getUsername());
        BlogResponseDto blogDto = blogService.findById(findUser.getBlog().getId());

        model.addAttribute("user", findUser);
        model.addAttribute("blog",blogDto);
        return "/post/insertPost";
    }
    @PostMapping("/post/save")
    public String save(@AuthenticationPrincipal PrincipalDetails principalDetails, PostSaveRequestDto requestDto){
        User findUser = principalDetails.getUser();

        postService.save(findUser, requestDto);
        return "redirect:/blog/blogMain/"+findUser.getBlog().getId();
    }

    @GetMapping("/post/update/{id}")
    public String updateForm(@PathVariable("id") Long postId, Model model){
        PostResponseDto postDto = postService.findById(postId);
        model.addAttribute("blog", postDto.getBlog());
        model.addAttribute("post",postDto);
        return "/post/getPost";
    }

    @PostMapping("/post/update/{id}")
    public String update(@PathVariable("id") Long postId, PostUpdateRequestDto requestDto){
        postService.update(postId, requestDto);
        PostResponseDto postDto = postService.findById(postId);
        return "redirect:/blog/blogMain/"+postDto.getBlog().getId();
    }

    @GetMapping("/post/delete/{id}")
    public String delete(@PathVariable("id") Long postId,@AuthenticationPrincipal PrincipalDetails principalDetails){
        User findUser = principalDetails.getUser();
        postService.delete(postId);
        return "redirect:/blog/blogMain/"+findUser.getBlog().getId();
    }
}
