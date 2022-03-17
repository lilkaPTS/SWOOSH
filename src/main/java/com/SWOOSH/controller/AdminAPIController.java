package com.SWOOSH.controller;


import com.SWOOSH.model.CarWash;
import com.SWOOSH.model.Employee;
import com.SWOOSH.repository.CarWashRepository;
import com.SWOOSH.repository.EmployeeRepository;
import com.SWOOSH.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdminAPIController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CarWashRepository carWashRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/createCarWash")
    public boolean createCarWash(CarWash carWash) {
        return adminService.createCarWash(carWash);
    }

    @PostMapping("/createEmployee")
    public boolean createEmployee(String name, String carWashLocation) {
        return adminService.createEmployee(name, carWashLocation);
    }
}
