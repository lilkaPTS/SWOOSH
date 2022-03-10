package com.SWOOSH.controller;

import com.SWOOSH.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRestController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/singIn")
    public boolean singIn(String email, String password) {
        return loginService.singIn(email, password);
    }
}
