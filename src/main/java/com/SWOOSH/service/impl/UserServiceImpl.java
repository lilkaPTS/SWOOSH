package com.SWOOSH.service.impl;

import com.SWOOSH.enums.Role;
import com.SWOOSH.enums.Status;
import com.SWOOSH.model.ConfirmationCode;
import com.SWOOSH.model.User;
import com.SWOOSH.model.UserSecurity;
import com.SWOOSH.repository.ConfirmationCodeRepository;
import com.SWOOSH.repository.UserRepository;
import com.SWOOSH.service.IUserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64Encoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userServiceImpl")
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final ConfirmationCodeRepository confirmationCodeRepository;
    private final EmailService emailService;

    @Override
    @Transactional
    public User createUser(User user) {
        if (isPresentEmail(user.getEmail())) {
            return null;
        }

        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        user.setRole(Role.CUSTOMER);
        user.setStatus(Status.CONFIRMATION);
//        sendConfirmationCode(user.getEmail());

        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(Long userId) {
        return userRepository.getById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    @Override
    @Transactional
    public User updateRole(Long userId, Role role) {
        User user = userRepository.getById(userId);
        user.setRole(role);

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateStatus(Long userId, Status status) {
        User user = userRepository.getById(userId);
        user.setStatus(status);

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User doesn't exists");
        }
        return UserSecurity.fromUser(user);
    }

    public Boolean isPresentEmail(String email) {
        return userRepository.existUserByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public User checkConfirmationCode(String email, String code) {
        ConfirmationCode confirmationCode = confirmationCodeRepository.getConfirmationCodeByEmail(email);
        User user = userRepository.findByEmail(email);
        if (confirmationCode != null && confirmationCode.getCode().equals(code)) {
            return updateStatus(user.getId(), Status.ACTIVE);
        }
        return null;
    }

    @Override
    @Transactional
    public void sendConfirmationCode(String email) {
        ConfirmationCode code = new ConfirmationCode();
        code.setCode(generateConfirmationCode(10));
        code.setEmail(email);
        emailService.sentEmail(code.getEmail(), code.getCode());
        if(confirmationCodeRepository.existByEmail(email)) {
            confirmationCodeRepository.updateCode(code.getEmail(), code.getCode());
        } else {
            confirmationCodeRepository.save(code);
        }
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
