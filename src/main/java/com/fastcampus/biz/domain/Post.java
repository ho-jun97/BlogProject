package com.fastcampus.biz.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Post extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="blog_id")
    private Blog blog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    @Builder
    public Post(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void setCategory(Category category){
        if(this.category != null){
            this.category.getPostList().remove(this);
        }
        this.category = category;
        if(!this.category.getPostList().contains(this)){
            this.category.addPost(this);
        }
    }
    public void setBlog(Blog blog){
        if(this.blog != null){
            this.blog.getPostList().remove(this);
        }
        this.blog = blog;
        if(!this.blog.getCategoryList().contains(this)){
            this.blog.addPost(this);
        }
    }
    public void update(String title, String content, Category category){
        this.title = title;
        this.content = content;
        this.category = category;
    }

}
