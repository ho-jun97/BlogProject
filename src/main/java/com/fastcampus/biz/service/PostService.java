package com.fastcampus.biz.service;

import com.fastcampus.biz.domain.Blog;
import com.fastcampus.biz.domain.Category;
import com.fastcampus.biz.domain.Post;
import com.fastcampus.biz.domain.User;
import com.fastcampus.biz.persistence.BlogRepository;
import com.fastcampus.biz.persistence.CategoryRepository;
import com.fastcampus.biz.persistence.PostRepository;
import com.fastcampus.web.controller.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final BlogRepository blogRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public void save(User user, PostSaveRequestDto requestDto){
        Blog blog = blogRepository.findById(user.getBlog().getId()).orElseThrow(
                ()->new IllegalArgumentException("해당 블로그가 없습니다."));
        Category category = categoryRepository.findByName(requestDto.getCategoryName(),blog.getId()).orElseThrow(
                ()->new IllegalArgumentException("해당 카테고리가 없습니다."));

        Post post = requestDto.toEntity();

        category.addPost(post);
        blog.addPost(post);

        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<PostListResponseDto> getPostList(String categoryName, Long blogId){
        List<Post> list = null;
        if(categoryName.equals("")){
            list = postRepository.findAllDesc(blogId);
        }else {
            list = postRepository.findByCategoryDesc(categoryName, blogId);
        }

        return list.stream().map(PostListResponseDto::new).collect(Collectors.toList());
    }

    public PostResponseDto findById(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new IllegalArgumentException("해당 포스트가 없습니다."));

        return new PostResponseDto(post);
    }

    @Transactional
    public void update(Long postId, PostUpdateRequestDto requestDto){
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new IllegalArgumentException("해당 포스트가 없습니다."));
        Category category = categoryRepository.findByName(requestDto.getCategoryName(), post.getBlog().getId()).orElseThrow(
                ()->new IllegalArgumentException("해당 카테고리가 없습니다."));

        post.update(requestDto.getTitle(), requestDto.getContent(), category);
    }

    @Transactional
    public void delete(Long postId){
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new IllegalArgumentException("해당 포스트가 없습니다."));
        postRepository.delete(post);
    }
}
