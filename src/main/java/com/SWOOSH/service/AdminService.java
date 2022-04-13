package com.SWOOSH.service;

import com.SWOOSH.dto.*;
import com.SWOOSH.enums.Role;
import com.SWOOSH.enums.Status;
import com.SWOOSH.model.CarWash;
import com.SWOOSH.model.Employee;
import com.SWOOSH.model.Order;
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

import java.util.*;
import java.util.stream.Collectors;

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



    public long getReturnAbility(String employeeName, String carWashLocation, Date start, Date end) {
        List<String> orders = orderRepository.getOrderForReturnAbility(
                employeeRepository.findByUser(userRepository.findByName(employeeName)),
                carWashRepository.getCarWashByLocation(carWashLocation),
                start,
                end).stream().map(order -> order.getUser().getEmail()).collect(Collectors.toList());
        Set<String> uniqueUsernames = new HashSet<>(orders);
        List<String> usernamesWithOneOrder = new ArrayList<>();
        for (String uniqueUser : uniqueUsernames) {
            int counter = 0;
            for (String order: orders) {
                if(counter>1) {
                    break;
                } else if(order.equals(uniqueUser)) {
                    counter++;
                }
            }
            if(counter==1) {
                usernamesWithOneOrder.add(uniqueUser);
            }
        }
        return Math.round(100 - ((double)usernamesWithOneOrder.size()/uniqueUsernames.size())*100);
    }

    public List<UserStatsDTO> getCustomerStats(String carWashLocation) {
        List<UserStatsDTO> result = new ArrayList<>();
        List<Order> orders = orderRepository
                .getOrdersByCarWash(carWashRepository.getCarWashByLocation(carWashLocation));
        Set<User> uniqueUsers = orders.stream().map(Order::getUser).collect(Collectors.toSet());
        uniqueUsers.forEach(user -> {
            List<Order> userOrders = orderRepository.getOrdersByUser(user);
            result.add(new UserStatsDTO(user.getEmail(),
                    orderRepository.countOrderByCarWashAndUser
                            (carWashRepository.getCarWashByLocation(carWashLocation), user),
                    (int) Math.round(userOrders
                            .stream()
                            .map(Order::getTotalPrice)
                            .reduce(Integer::sum).get()/(double)userOrders.size())
            ));
        });
        return result;
    }
}
