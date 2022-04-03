package com.SWOOSH.service;

import com.SWOOSH.model.CarWash;
import com.SWOOSH.model.Employee;
import com.SWOOSH.model.Service;
import com.SWOOSH.model.User;
import java.util.List;

public interface IAdminService {

    CarWash createCarWash(CarWash carWash);

    CarWash addServices(Long carWashId, List<Service> services);

    CarWash addEmployees(Long carWashId, List<Employee> employees);

//    List<String> getAllEmployeesByCarWash(String location);

    Integer getNumberEmployeeOrders(String carWashLocation, String name, String passportData);

    List<String> getAllCarWashes();

    Employee createEmployee(Long carWashId, User user);

    User createAdmin(User user);
}
