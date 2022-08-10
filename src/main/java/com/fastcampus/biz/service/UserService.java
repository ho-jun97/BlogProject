package com.fastcampus.biz.service;

import com.fastcampus.biz.domain.User;
import com.fastcampus.biz.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public User findByUser(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }
}
