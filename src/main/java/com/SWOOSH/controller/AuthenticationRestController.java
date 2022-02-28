package com.SWOOSH.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationRestController {
    @GetMapping("/test")
    public String test() {
        return "123231";
    }
}
