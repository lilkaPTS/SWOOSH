package com.SWOOSH.controller;

import com.SWOOSH.model.User;
import com.SWOOSH.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationRestController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/createUser")
    public User createUser(User user) {
        return registrationService.create(user);
    }

    @PostMapping("/emailCheck")
    public boolean isPresentEmail(String email) {
        return registrationService.isPresentEmail(email);
    }

    @PostMapping("/getConfirmationCode")
    public String sendConfirmationCode(String email) {
        return registrationService.sendConfirmationCode(email);
    }
}
