package com.SWOOSH.controller.regauth;

import com.SWOOSH.dto.UserFullDto;
import com.SWOOSH.dto.UserWithPasswordDto;
import com.SWOOSH.model.User;
import com.SWOOSH.service.IOrderService;
import com.SWOOSH.service.IUserService;
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

    private final IUserService userService;
    private final ConversionService conversionService;

    @PostMapping(value = "/createUser")
    public UserFullDto createUser(@RequestBody UserWithPasswordDto userDto) {
        User user = conversionService.convert(userDto, User.class);
        user = userService.createUser(user);
        return conversionService.convert(user, UserFullDto.class);
    }

    @PutMapping("/checkConfirmation")
    public  ResponseEntity<?> checkConfirmationCode(String code, String email) {
        User user = userService.checkConfirmationCode(email, code);
        if (user != null) {
            return new ResponseEntity<>(conversionService.convert(user, UserFullDto.class), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/sendConfirmation")
    public void sendConfirmationCode(String email) {
        userService.sendConfirmationCode(email);
    }
}
