package com.SWOOSH.service;

import com.SWOOSH.model.CarWash;
import com.SWOOSH.model.Employee;
import com.SWOOSH.model.Service;
import com.SWOOSH.model.User;
import java.util.List;

public interface IAdminService {

    CarWash createCarWash(CarWash carWash);

    CarWash addServices(String carWashLocation, List<Service> services);

    CarWash addEmployees(Long carWashId, List<Employee> employees);

    //List<String> getAllEmployeesByCarWash(String location);

    //Integer getNumberEmployeeOrders(String email, String carWashLocation);

    List<String> getAllCarWashes();

    Employee createEmployee(String carWashLocation, User user);

    User createAdmin(User user);
}
