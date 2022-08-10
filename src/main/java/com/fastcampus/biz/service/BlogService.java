package com.fastcampus.biz.service;

import com.fastcampus.biz.domain.Blog;
import com.fastcampus.biz.domain.Category;
import com.fastcampus.biz.domain.User;
import com.fastcampus.biz.persistence.BlogRepository;
import com.fastcampus.biz.persistence.UserRepository;
import com.fastcampus.web.controller.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    @Transactional
    public User insertBlog(String username, BlogSaveRequestDto requestDto){
        User user  = userRepository.findByUsername(username).orElseThrow(
                ()-> new IllegalArgumentException("해당 유저가 없습니다."));

        Blog blog = requestDto.toEntity();
        blog.setUser(user);
        blog.addCategory(new Category());

        Blog resultBlog = blogRepository.save(blog);
        return user;
    }
    @Transactional(readOnly = true)
    public List<BlogListResponseDto> getBlogList(String searchCondition, String searchKeyword){
        List<Blog> list = null;
        if(!searchCondition.equals("")) {
            if (searchCondition.equals("TITLE")) {
                list = blogRepository.findByTitleDesc(searchKeyword);
            } else {
                list = blogRepository.findByTagDesc(searchKeyword);
            }
        }else{
            list = blogRepository.findAllDesc();
        }
        return list.stream().map(BlogListResponseDto::new).collect(Collectors.toList());
    }

    public BlogResponseDto findById(Long blogId){
        Blog blog = blogRepository.findById(blogId).orElseThrow(
                ()-> new IllegalArgumentException("해당 블로그가 없습니다."));

        return new BlogResponseDto(blog);
    }

    @Transactional
    public void delete(Long blogId){
        Blog blog = blogRepository.findById(blogId).orElseThrow(
                ()-> new IllegalArgumentException("해당 블로그가 없습니다."));
        blog.getUser().deleteBlog();
        blogRepository.delete(blog);
    }

    @Transactional
    public void deleteStatus(Long blogId){
        Blog blog = blogRepository.findById(blogId).orElseThrow(
                ()-> new IllegalArgumentException("해당 블로그가 없습니다."));
        blog.deleteStatus();
    }

    @Transactional
    public void update(Long blogId, BlogUpdateRequestDto requestDto){
        Blog blog = blogRepository.findById(blogId).orElseThrow(
                ()-> new IllegalArgumentException("해당 블로그가 없습니다."));
        blog.update(requestDto.getTitle(), requestDto.getTag());
    }
}
