package com.SWOOSH.repository;

import com.SWOOSH.model.CarWash;
import com.SWOOSH.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> getEmployeeByNameAndCarWash(String name, CarWash carWash);
    List<Employee> getEmployeesByCarWash(CarWash carWash);
}
