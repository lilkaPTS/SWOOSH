package com.SWOOSH.service;

import com.SWOOSH.model.ConfirmationCode;
import com.SWOOSH.model.User;
import com.SWOOSH.repository.ConfirmationCodeRepository;
import com.SWOOSH.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationCodeRepository confirmationCodeRepository;

    @Autowired
    private EmailService emailService;

    public User create(User user) {
        return userRepository.save(user);
    }

    public boolean isPresentEmail(String email) {
        return userRepository.countUserByEmail(email) == 0;
    }

    public boolean checkConfirmationCode(String email, String code) {
        Optional<ConfirmationCode> confirmationCode = confirmationCodeRepository.getConfirmationCodeByEmail(email);
        return confirmationCode.isPresent() && confirmationCode.get().getCode().equals(code);
    }

    public void sendAndSaveConfirmationCode(String email) {
        ConfirmationCode code = new ConfirmationCode();
        code.setCode(generateConfirmationCode(10));
        code.setEmail(email);
        if(confirmationCodeRepository.countByEmail(email)!=0) {
            confirmationCodeRepository.updateCode(code.getEmail(), code.getCode());
        } else {
            confirmationCodeRepository.save(code);
        }

        emailService.sentEmail(code.getEmail(), code.getCode());
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
