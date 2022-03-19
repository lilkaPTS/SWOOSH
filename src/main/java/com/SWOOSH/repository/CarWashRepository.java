package com.SWOOSH.repository;

import com.SWOOSH.model.CarWash;
import com.SWOOSH.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarWashRepository extends JpaRepository<CarWash, Integer> {
    int countCarWashByLocation(String location);
    Optional<CarWash> getCarWashByLocation(String location);
    @Query(value = "SELECT location FROM car_washes", nativeQuery = true)
    List<String> getAll();
}
