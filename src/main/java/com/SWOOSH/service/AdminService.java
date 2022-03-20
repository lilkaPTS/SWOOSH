package com.SWOOSH.service;

import com.SWOOSH.model.CarWash;
import com.SWOOSH.model.Employee;
import com.SWOOSH.model.User;
import com.SWOOSH.repository.CarWashRepository;
import com.SWOOSH.repository.EmployeeRepository;
import com.SWOOSH.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private CarWashRepository carWashRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private OrderRepository orderRepository;

    public boolean createCarWash(CarWash carWash) {
        if(isPresentCarWashLocation(carWash.getLocation())) {
            carWashRepository.save(carWash);
            return true;
        }
        else {
            return false;
        }
    }

    public List<String> getAllEmployeesByCarWash(String location) {
        List<String> result = new ArrayList<>();
        Optional<CarWash> carWash = carWashRepository.getCarWashByLocation(location);
        if(carWash.isPresent()) {
            List<Employee> employees = employeeRepository.getEmployeesByCarWash(carWash.get());
            employees.forEach(employee -> result.add(employee.getName()));
        }
        return result;
    }

    public Integer getNumberEmployeeOrders(String carWashLocation, String name, String passportData) {
        return 0;
    }


    public List<String> getAllCarWashes() {
        return carWashRepository.getAll();
    }

    public boolean createEmployee(String name, String carWashLocation, String passportData) {
        Optional<CarWash> carWash = carWashRepository.getCarWashByLocation(carWashLocation);
        if(carWash.isPresent()) {
            Optional<Employee> employee = employeeRepository.getEmployeeByPassportDataAndCarWash(passportData, carWash.get());
            if(employee.isEmpty()) {
                employeeRepository.save(new Employee(carWash.get(), name, passportData));
                return true;
            }
        }
        return false;
    }


    public boolean isPresentCarWashLocation(String location) {
        return carWashRepository.countCarWashByLocation(location)==0;
    }
}
