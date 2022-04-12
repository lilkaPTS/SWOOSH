package com.SWOOSH.controller.regauth;

import com.SWOOSH.dto.RegistrationDTO;
import com.SWOOSH.model.User;
import com.SWOOSH.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/reg")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping(value = "/createUser")
    public Boolean createUser(@RequestBody RegistrationDTO registrationDTO) {
        return userService.createUser(registrationDTO);
    }

    @PutMapping("/checkConfirmation")
    public Boolean checkConfirmationCode(String code, String email) {
        return userService.checkConfirmationCode(email, code);
    }

    @PostMapping("/sendConfirmation")
    public void sendConfirmationCode(String email) {
        userService.sendConfirmationCode(email);
    }
}
