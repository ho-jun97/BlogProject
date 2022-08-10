package com.fastcampus.auth;

import com.fastcampus.biz.domain.Blog;
import com.fastcampus.biz.domain.Role;
import com.fastcampus.biz.domain.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class PrincipalDetails implements UserDetails {

    private User user;

    public PrincipalDetails(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole().toString();
            }
        });
        return collect;
    }
    public Blog getBlog(){
        return user.getBlog();
    }
    public Long getId(){
        return user.getId();
    }
    public Role getRole(){
        return user.getRole();
    }
    public void setUser(User user){
        this.user = user;
    }
    @Override
    public String getPassword() {
        //{noop} -> 비밀번호를 자동으로 암호화 X
        return "{noop}" + user.getPassword();
    }
    @Override
    public String getUsername() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
