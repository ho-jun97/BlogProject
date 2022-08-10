package com.fastcampus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/","/blog/blogMain/**","/auth/**", "/images/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/auth/loginForm")
                    .loginProcessingUrl("/auth/login")
                    .defaultSuccessUrl("/")
                .and()
                .logout()
                    .logoutUrl("/auth/logout")
                    .logoutSuccessUrl("/");

        return http.build();
    }
}
