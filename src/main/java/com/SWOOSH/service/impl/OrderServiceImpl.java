package com.SWOOSH.service.impl;

import com.SWOOSH.model.CarWash;
import com.SWOOSH.model.Order;
import com.SWOOSH.model.Review;
import com.SWOOSH.model.User;
import com.SWOOSH.repository.CarWashRepository;
import com.SWOOSH.repository.EmployeeRepository;
import com.SWOOSH.repository.OrderRepository;
import com.SWOOSH.repository.ReviewRepository;
import com.SWOOSH.repository.ServiceRepository;
import com.SWOOSH.repository.UserRepository;
import com.SWOOSH.service.IOrderService;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CarWashRepository carWashRepository;
    private final ServiceRepository serviceRepository;
    private final EmployeeRepository employeeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Order createOrder(String email, String location, List<String> serviceNames) {
        User user = userRepository.findByEmail(email);
        CarWash carWash = carWashRepository.getCarWashByLocation(location);
        List<com.SWOOSH.model.Service> services = serviceRepository.findAllByName(serviceNames);
        Order order = new Order();

        order.setOrderedServices(services);
        order.setCarWash(carWash);
        order.setUser(user);
        order.setDate(new Date());
        order.setTotalPrice(
                services.stream()
                        .map(com.SWOOSH.model.Service::getPrice)
                        .reduce(0, Integer::sum)
        );

        return orderRepository.save(order);
    }

    @Override
    public Order acceptOrder(Order order, Long employeeId) {
        User employee = userRepository.getById(employeeId);
        order.setEmployee(employee);
        return orderRepository.save(order);
    }

    @Override
    public Order gradeOrder(Long orderId, Double grade, Review review) {
        Order order = orderRepository.getById(orderId);
        order.setGrade(grade);
        reviewRepository.save(review);
        return orderRepository.save(order);
    }
}
