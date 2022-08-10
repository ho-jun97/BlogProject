package com.fastcampus.biz.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String tag;
    private String title;

    @OneToOne(mappedBy = "blog", fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "blog" , cascade = CascadeType.ALL)
    @OrderBy("id desc") // 리스트 내림차순 정렬
    private List<Category> categoryList = new ArrayList<>();

    @OneToMany(mappedBy = "blog" , cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    @Builder
    public Blog(String status, String tag, String title){
        this.status = status;
        this.tag = tag;
        this.title = title;
    }

    public void update(String title, String tag){
        this.title = title;
        this.tag = tag;
    }

    public void setUser(User user){
        this.user = user;
        user.setBlog(this);
    }

    public void addPost(Post post){
        this.postList.add(post);
        if(post.getBlog() != this) {
            post.setBlog(this);
        }
    }

    public void addCategory(Category category){
        this.categoryList.add(category);
        if(category.getBlog() != this) {
            category.setBlog(this);
        }
    }

    public void deleteStatus(){
        this.status = "삭제 요청";
    }
}
