package com.fastcampus.biz.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private DisplayType displayType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id")
    private Blog blog;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    @Builder
    public Category(String name, String description, DisplayType displayType){
        this.name = name;
        this.description = description;
        this.displayType = displayType;
    }
    public Category(){
        this.name = "미분류";
        this.description ="기본으로 제공되는 카테고리 입니다.";
        this.displayType = DisplayType.MIXED;
    }
    public void addPost(Post post){
        this.postList.add(post);
        if(post.getCategory() != this) {
            post.setCategory(this);
        }
    }
    public void setBlog(Blog blog){
        if(this.blog != null){
            this.blog.getCategoryList().remove(this);
        }
        this.blog = blog;
        if(!this.blog.getCategoryList().contains(this)){
            this.blog.addCategory(this);
        }
    }
    public void update(String name, String description, DisplayType displayType){
        this.name = name;
        this.description = description;
        this.displayType = displayType;
    }

}
