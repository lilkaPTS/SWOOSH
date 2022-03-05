package com.SWOOSH.service;

import com.SWOOSH.model.User;
import com.SWOOSH.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public User create(User user) {
        return userRepository.save(user);
    }

    public boolean isPresentEmail(String email) {
        return userRepository.countUserByEmail(email) == 0;
    }

    public String sendConfirmationCode(String email) {
        String code = generateConfirmationCode(10);
        emailService.sentEmail(email, generateConfirmationCode(10));
        return code;
    }

    private String generateConfirmationCode(int codeLength) {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            int number = (int) (Math.random()*10);
            code.append(number%2 == 0 ? String.valueOf((char) ((Math.random()*26)+65)) : String.valueOf(number));
        }
        return code.toString();
    }
}
