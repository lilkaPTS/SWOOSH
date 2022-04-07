package com.SWOOSH.service;

import com.SWOOSH.dto.RegistrationDTO;
import com.SWOOSH.enums.Role;
import com.SWOOSH.enums.Status;
import com.SWOOSH.model.ConfirmationCode;
import com.SWOOSH.model.User;
import com.SWOOSH.model.UserSecurity;
import com.SWOOSH.repository.ConfirmationCodeRepository;
import com.SWOOSH.repository.UserRepository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("userServiceImpl")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ConfirmationCodeRepository confirmationCodeRepository;
    private final EmailService emailService;

    public Boolean createUser(RegistrationDTO registrationDTO) {
        if (isPresentEmail(registrationDTO.getEmail())) {
            return false;
        }
        User user = new User();
        user.setEmail(registrationDTO.getEmail());
        user.setName(registrationDTO.getName());
        user.setPassword(new BCryptPasswordEncoder(12).encode(registrationDTO.getPassword()));
        user.setRole(Role.CUSTOMER);
        user.setStatus(Status.CONFIRMATION);
        sendConfirmationCode(registrationDTO.getEmail());
        userRepository.save(user);
        return true;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailWithStatusActive(email);
        if (user == null) {
            throw new UsernameNotFoundException("User doesn't exists");
        }
        return UserSecurity.fromUser(user);
    }

    public User updateStatus(Long userId, Status status) {
        User user = userRepository.getById(userId);
        user.setStatus(status);
        return userRepository.save(user);
    }

    public Boolean isPresentEmail(String email) {
        return userRepository.existUserByEmail(email);
    }

    public Boolean checkConfirmationCode(String email, String code) {
        ConfirmationCode confirmationCode = confirmationCodeRepository.getConfirmationCodeByEmail(email);
        User user = userRepository.findByEmail(email);
        if (confirmationCode != null && confirmationCode.getCode().equals(code)) {
            userRepository.save(updateStatus(user.getId(), Status.ACTIVE));
            return true;
        }
        return false;
    }

    public void sendConfirmationCode(String email) {
        Thread thread = new Thread(() -> {
            ConfirmationCode code = new ConfirmationCode();
            code.setCode(generateConfirmationCode(10));
            code.setEmail(email);
            emailService.sentEmail(code.getEmail(), code.getCode());
            if(confirmationCodeRepository.existByEmail(email)) {
                confirmationCodeRepository.updateCode(code.getEmail(), code.getCode());
            } else {
                confirmationCodeRepository.save(code);
            }
        });
        thread.start();
    }

    private String generateConfirmationCode(Integer codeLength) {
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < codeLength; i++) {
            int number = (int) (Math.random()*10);
            code.append(number%2 == 0 ? String.valueOf((char) ((Math.random()*26)+65)) : String.valueOf(number));
        }
        return code.toString();
    }
}
