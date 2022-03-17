package com.SWOOSH.repository;

import com.SWOOSH.model.CarWash;
import com.SWOOSH.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarWashRepository extends JpaRepository<CarWash, Integer> {
    int countCarWashByLocation(String location);
    Optional<CarWash> getCarWashByLocation(String location);
}
