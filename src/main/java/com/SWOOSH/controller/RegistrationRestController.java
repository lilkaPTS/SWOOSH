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

    @PostMapping("/sendConfirmationCode")
    public void sendConfirmationCode(String email) {
        registrationService.sendAndSaveConfirmationCode(email);
    }

    @PostMapping("/checkConfirmationCode")
    public boolean checkConfirmationCode(String email, String code) {
       return registrationService.checkConfirmationCode(email, code);
    }
}
