package com.SWOOSH.controller.protect;


import com.SWOOSH.dto.*;
import com.SWOOSH.model.Order;
import com.SWOOSH.repository.OrderRepository;

import com.SWOOSH.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final ConversionService conversionService;
    private final OrderRepository orderRepository;

    @PostMapping("/createCarWash")
    //@PreAuthorize("hasAnyAuthority('ADMIN_PERMISSION')")
    public Boolean createCarWash(@RequestBody CarWashDTO carWashDTO) {
        return adminService.createCarWash(carWashDTO);
    }

    @PostMapping("/createEmployee")
    //@PreAuthorize("hasAnyAuthority('ADMIN_PERMISSION')")
    public void createEmployee(RegistrationEmployeeDTO registrationDTO) {
        adminService.createEmployee(registrationDTO);
    }

    @PostMapping("/addServices")
    //@PreAuthorize("hasAnyAuthority('ADMIN_PERMISSION')")
    public void addService(ServiceAddDTO serviceDTO) {
        adminService.createService(serviceDTO);
    }


    @GetMapping("/getAllCarWashes")
    //@PreAuthorize("hasAnyAuthority('ADMIN_PERMISSION')")
    public List<String> getAllCarWashes() {
        return adminService.getAllCarWashes();
    }

    @GetMapping("/getAllEmployees")
    //@PreAuthorize("hasAnyAuthority('ADMIN_PERMISSION')")
    public List<String> getAllEmployees(String location) {
        return adminService.getAllEmployeesByCarWash(location);
    }

    @GetMapping("/getNumberEmployeeOrders")
    //@PreAuthorize("hasAnyAuthority('ADMIN_PERMISSION')")
    public Integer getNumberEmployeeOrders(String employeeName,
                                           String carWashLocation,
                                           @DateTimeFormat(pattern = "dd-MM-yyyy") Date start,
                                           @DateTimeFormat(pattern = "dd-MM-yyyy") Date end
    ) {
        return adminService.getNumberEmployeeOrders(employeeName, carWashLocation, start, end);
    }

    @GetMapping("/getReturnAbility")
    //@PreAuthorize("hasAnyAuthority('ADMIN_PERMISSION')")
    public long getReturnAbility(String employeeName,
                                    String carWashLocation,
                                    @DateTimeFormat(pattern = "dd-MM-yyyy") Date start,
                                    @DateTimeFormat(pattern = "dd-MM-yyyy") Date end) {
        return adminService.getReturnAbility(employeeName, carWashLocation, start, end);
    }
}
