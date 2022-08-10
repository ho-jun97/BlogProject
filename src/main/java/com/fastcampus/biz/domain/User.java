package com.fastcampus.biz.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Table(name="BLOG_USER")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="blog_id")
    private Blog blog;

    public void setBlog(Blog blog){
        this.blog = blog;
    }
    public void deleteBlog(){
        blog = null;
    }
}
