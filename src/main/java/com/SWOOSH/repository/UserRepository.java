package com.SWOOSH.repository;

import com.SWOOSH.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    int countUserByEmail(String email);
    Optional<User> getUserByEmail(String email);
}
