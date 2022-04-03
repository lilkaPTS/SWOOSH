package com.SWOOSH.repository;

import com.SWOOSH.model.CarWash;
import com.SWOOSH.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> getEmployeesByCarWash(CarWash carWash);
}
