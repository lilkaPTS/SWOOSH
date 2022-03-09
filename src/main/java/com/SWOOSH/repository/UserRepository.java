package com.SWOOSH.repository;

import com.SWOOSH.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    int countUserByEmail(String email);
}
