package com.SWOOSH.repository;

import com.SWOOSH.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    public int countUserByEmail(String email);
}
