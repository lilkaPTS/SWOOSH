package com.SWOOSH.controller;


import com.SWOOSH.model.CarWash;
import com.SWOOSH.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/getNumberEmployeeOrders")
    public Integer getNumberEmployeeOrders(String name, String carWashLocation, @RequestParam(required = false) String passportData) {
        return adminService.getNumberEmployeeOrders(carWashLocation,name, passportData.isEmpty()? "" : passportData);
    }

    @PostMapping("/createEmployee")
    public boolean createEmployee(String name, String carWashLocation, String passportData) {
        return adminService.createEmployee(name, carWashLocation, passportData);
    }

}
