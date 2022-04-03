package com.SWOOSH.service;

import com.SWOOSH.model.Order;
import com.SWOOSH.model.Review;
import java.util.List;

public interface IOrderService {

    Order createOrder(String email, String location, List<String> services);

    Order acceptOrder(Order order, Long employeeId);

    Order gradeOrder(Long orderId, Double grade, Review review);
}
