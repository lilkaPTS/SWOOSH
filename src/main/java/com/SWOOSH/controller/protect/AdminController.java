package com.SWOOSH.controller.protect;


import com.SWOOSH.dto.CarWashDto;
import com.SWOOSH.dto.EmployeeDto;
import com.SWOOSH.dto.ServiceDto;
import com.SWOOSH.dto.UserDto;
import com.SWOOSH.dto.UserFullDto;
import com.SWOOSH.model.CarWash;
import com.SWOOSH.model.Employee;
import com.SWOOSH.model.Service;
import com.SWOOSH.model.User;
import com.SWOOSH.repository.OrderRepository;
import com.SWOOSH.service.IAdminService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final IAdminService adminService;
    private final ConversionService conversionService;
    private final OrderRepository orderRepository;

    @PostMapping("/createCarWash")
    //@PreAuthorize("hasAnyAuthority('ADMIN_PERMISSION')")
    public CarWashDto createCarWash(@RequestBody @Valid CarWashDto carWashDto) {
        CarWash carWash = conversionService.convert(carWashDto, CarWash.class);
        carWash = adminService.createCarWash(carWash);
        return conversionService.convert(carWash, CarWashDto.class);
    }

    @PostMapping("/createEmployee")
    //@PreAuthorize("hasAnyAuthority('ADMIN_PERMISSION')")
    public EmployeeDto createEmployee(@RequestParam String carWashLocation, @RequestBody @Valid UserDto userDto) {
        User user = conversionService.convert(userDto, User.class);
        Employee employee = adminService.createEmployee(carWashLocation, user);
        return conversionService.convert(employee, EmployeeDto.class);
    }

    @PostMapping("/createAdmin")
    //@PreAuthorize("hasAnyAuthority('ADMIN_PERMISSION')")
    public UserFullDto createAdmin(@RequestBody @Valid UserDto userDto) {
        User user = conversionService.convert(userDto, User.class);
        user = adminService.createAdmin(user);
        return conversionService.convert(user, UserFullDto.class);
    }

    @PostMapping("/addServices")
    //@PreAuthorize("hasAnyAuthority('ADMIN_PERMISSION')")
    public CarWashDto addServices(@RequestParam String carWashLocation, @RequestBody List<ServiceDto> servicesDto) {
        List<Service> services = servicesDto.stream()
                .map(e -> conversionService.convert(e, Service.class))
                .collect(Collectors.toList());
        CarWash carWash = adminService.addServices(carWashLocation, services);
        return conversionService.convert(carWash, CarWashDto.class);
    }

    @PostMapping("/addEmployees")
    //@PreAuthorize("hasAnyAuthority('ADMIN_PERMISSION')")
    public CarWashDto addEmployees(@RequestParam Long carWashId, List<EmployeeDto> employeesDto) {
        List<Employee> employees = employeesDto.stream()
                .map(e -> conversionService.convert(e, Employee.class))
                .collect(Collectors.toList());
        CarWash carWash = adminService.addEmployees(carWashId, employees);
        return conversionService.convert(carWash, CarWashDto.class);
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
}
