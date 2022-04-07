package com.SWOOSH.service;

import com.SWOOSH.dto.CreateOrderDTO;
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

import java.util.Date;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CarWashRepository carWashRepository;
    private final ServiceRepository serviceRepository;
    private final EmployeeRepository employeeRepository;
    private final ReviewRepository reviewRepository;

    public Long createOrder(CreateOrderDTO createOrderDTO) {
        User user = userRepository.findByEmailWithStatusActive(createOrderDTO.getEmail());
        CarWash carWash = carWashRepository.getCarWashByLocation(createOrderDTO.getLocation());
        List<com.SWOOSH.model.Service> services = serviceRepository.findAllByName(createOrderDTO.getServices());
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
        return orderRepository.save(order).getOrderId();
    }

//    public Order acceptOrder(Order order, Long employeeId) {
//        User employee = userRepository.getById(employeeId);
//        Order oldOrder = orderRepository.getById(order.getOrderId());
//        oldOrder.setEmployee(employee);
//        return orderRepository.save(oldOrder);
//    }

    public Boolean gradeOrder(Long orderId, Integer grade, String text) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setGrade((double) grade);
            orderRepository.save(order);
            Review review = new Review();
            User user = userRepository.getById(order.getUser().getId());
            CarWash carWash = carWashRepository.getById(order.getCarWash().getCarWashId());
            review.setUser(user);
            review.setCarWash(carWash);
            review.setReviewText(text);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }
}
