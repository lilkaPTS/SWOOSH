package com.SWOOSH.controller;


import com.SWOOSH.model.CarWash;
import com.SWOOSH.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdminAPIController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/createCarWash")
    public boolean createCarWash(CarWash carWash) {
        return adminService.createCarWash(carWash);
    }

    @PostMapping("/getAllCarWashes")
    public List<String> getAllCarWashes() {
        return adminService.getAllCarWashes();
    }

    @PostMapping("/getAllEmployeesByCarWash")
    public List<String> getAllEmployeesByCarWash(String location) {
        return adminService.getAllEmployeesByCarWash(location);
    }

    @PostMapping("/createEmployee")
    public boolean createEmployee(String name, String carWashLocation) {
        return adminService.createEmployee(name, carWashLocation);
    }

}
