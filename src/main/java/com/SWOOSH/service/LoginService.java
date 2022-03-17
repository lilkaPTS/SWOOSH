package com.SWOOSH.service;

import com.SWOOSH.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public boolean signIn(String email, String password) {
        return userRepository.getUserByEmail(email).map(user -> user.getPassword().equals(password)).orElse(false);
    }
}
