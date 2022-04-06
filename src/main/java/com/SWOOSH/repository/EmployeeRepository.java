package com.SWOOSH.repository;

import com.SWOOSH.model.CarWash;
import com.SWOOSH.model.Employee;
import java.util.List;

import com.SWOOSH.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> getEmployeesByCarWash(CarWash carWash);

    Employee findByUser(User user);
}
