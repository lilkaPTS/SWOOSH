package com.SWOOSH.service;

import com.SWOOSH.enums.Role;
import com.SWOOSH.enums.Status;
import com.SWOOSH.model.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {

    User createUser(User user);

    User getUser(Long userId);

    User getUser(String email);

    List<User> getAllUsers();

    User updateRole(Long userId, Role role);

    User updateStatus(Long userId, Status status);

    User checkConfirmationCode(String email, String code);

    void sendConfirmationCode(String email);
}
