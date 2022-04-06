package com.SWOOSH.service.impl;

import com.SWOOSH.enums.Role;
import com.SWOOSH.model.CarWash;
import com.SWOOSH.model.Employee;
import com.SWOOSH.model.User;
import com.SWOOSH.repository.CarWashRepository;
import com.SWOOSH.repository.EmployeeRepository;
import com.SWOOSH.repository.OrderRepository;
import com.SWOOSH.repository.ServiceRepository;
import com.SWOOSH.repository.UserRepository;
import com.SWOOSH.service.IAdminService;
import com.SWOOSH.service.IUserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminServiceImpl implements IAdminService {

    private final CarWashRepository carWashRepository;
    private final IUserService userService;
    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final ServiceRepository serviceRepository;
    private final OrderRepository orderRepository;

    @Override
    public CarWash createCarWash(CarWash carWash) {
        if (isPresentCarWashLocation(carWash.getLocation())) {
            return null;
        }
        return carWashRepository.save(carWash);
    }

    @Override
    public CarWash addServices(String carWashLocation, List<com.SWOOSH.model.Service> services) {
        CarWash carWash = carWashRepository.getCarWashByLocation(carWashLocation);
        carWash.setServices(services);

        // is it necessary to do this, since there are @ManyToOne?
        services.forEach(e -> {
            e.setCarWash(carWash);
            serviceRepository.save(e);
        });

        return carWashRepository.save(carWash);
    }

    @Override
    public CarWash addEmployees(Long carWashId, List<Employee> employees) {
        CarWash carWash = carWashRepository.getById(carWashId);
        carWash.setEmployee(employees);

        return carWashRepository.save(carWash);
    }


    @Override
    public List<String> getAllCarWashes() {
        return carWashRepository.getAllLocations();
    }

    @Override
    public Employee createEmployee(String carWashLocation, User user) {
        User oldUser = userRepository.findByEmailWithStatusActive(user.getEmail());
        CarWash carWash = carWashRepository.getCarWashByLocation(carWashLocation);
        oldUser = userService.updateRole(oldUser.getId(), Role.EMPLOYEE);

        Employee employee = new Employee();
        employee.setCarWash(carWash);
        employee.setUser(oldUser);

        return employeeRepository.save(employee);
    }

    @Override
    public User createAdmin(User user) {
        User oldUser = userRepository.findByEmailWithStatusActive(user.getEmail());
        return userService.updateRole(oldUser.getId(), Role.ADMIN);
    }


    private Boolean isPresentCarWashLocation(String location) {
        return carWashRepository.existCarWashByLocation(location);
    }
}
