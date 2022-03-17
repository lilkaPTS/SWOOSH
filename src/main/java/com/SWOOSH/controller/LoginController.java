package com.SWOOSH.controller;

import com.SWOOSH.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/signIn")
    public boolean signIn(String email, String password) {
        return loginService.signIn(email, password);
    }
}
