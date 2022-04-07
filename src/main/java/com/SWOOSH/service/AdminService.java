package com.SWOOSH.service;

import com.SWOOSH.dto.*;
import com.SWOOSH.enums.Role;
import com.SWOOSH.enums.Status;
import com.SWOOSH.model.CarWash;
import com.SWOOSH.model.Employee;
import com.SWOOSH.model.User;
import com.SWOOSH.repository.CarWashRepository;
import com.SWOOSH.repository.EmployeeRepository;
import com.SWOOSH.repository.OrderRepository;
import com.SWOOSH.repository.ServiceRepository;
import com.SWOOSH.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final CarWashRepository carWashRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final ServiceRepository serviceRepository;
    private final OrderRepository orderRepository;

    public boolean createCarWash(CarWashDTO carWashDTO) {
        if (carWashRepository.existCarWashByLocation(carWashDTO.getLocation())) {
            return false;
        }
        CarWash carWash = new CarWash();
        carWash.setName(carWashDTO.getName());
        carWash.setLocation(carWashDTO.getLocation());
        carWash.setNumber(carWashDTO.getNumber());
        System.out.println(carWash);
        carWashRepository.save(carWash);
        carWashDTO.getServices().forEach(serviceDTO -> {
            ServiceAddDTO serviceAddDTO = new ServiceAddDTO();
            serviceAddDTO.setName(serviceDTO.getName());
            serviceAddDTO.setPrise(serviceDTO.getPrise());
            serviceAddDTO.setCarWashLocation(carWashDTO.getLocation());
            createService(serviceAddDTO);
        });
        carWashDTO.getEmployees().forEach(registrationDTO -> {
            RegistrationEmployeeDTO registrationEmployeeDTO = new RegistrationEmployeeDTO();
            registrationEmployeeDTO.setEmail(registrationDTO.getEmail());
            registrationEmployeeDTO.setName(registrationDTO.getName());
            registrationEmployeeDTO.setPassword(new BCryptPasswordEncoder(12).encode(registrationDTO.getPassword()));
            registrationEmployeeDTO.setCarWashLocation(carWashDTO.getLocation());
            createEmployee(registrationEmployeeDTO);
        });
        return true;
    }

    public void createEmployee(RegistrationEmployeeDTO registrationDTO) {
        User user = new User();
        user.setEmail(registrationDTO.getEmail());
        user.setName(registrationDTO.getName());
        user.setPassword(registrationDTO.getPassword());
        user.setRole(Role.EMPLOYEE);
        user.setStatus(Status.ACTIVE);
        userRepository.save(user);
        CarWash carWash = carWashRepository.getCarWashByLocation(registrationDTO.getCarWashLocation());
        Employee employee = new Employee();
        employee.setUser(user);
        employee.setCarWash(carWash);
        employeeRepository.save(employee);
    }

    public void createService(ServiceAddDTO serviceDTO) {
        com.SWOOSH.model.Service service = new com.SWOOSH.model.Service();
        service.setName(serviceDTO.getName());
        service.setPrice(serviceDTO.getPrise());
        service.setCarWash(carWashRepository.getCarWashByLocation(serviceDTO.getCarWashLocation()));
        serviceRepository.save(service);
    }

    public List<String> getAllCarWashes() {
        return carWashRepository.getAllLocations();
    }

    public List<String> getAllEmployeesByCarWash(String location) {
        List<String> result = new ArrayList<>();
        CarWash carWash = carWashRepository.getCarWashByLocation(location);
        List<Employee> employees = employeeRepository.getEmployeesByCarWash(carWash);
        employees.forEach(e -> result.add(e.getUser().getName()));
        return result;
    }

    public Integer getNumberEmployeeOrders(String name, String carWashLocation, Date start, Date end) {
        User user = userRepository.findByName(name);
        Employee employee = employeeRepository.findByUser(user);
        CarWash carWash = carWashRepository.getCarWashByLocation(carWashLocation);
        return orderRepository.countOrders(employee, carWash, start, end);
    }
}
