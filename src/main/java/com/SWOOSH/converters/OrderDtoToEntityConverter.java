package com.SWOOSH.converters;

import com.SWOOSH.dto.OrderDto;
import com.SWOOSH.model.Order;
import org.springframework.core.convert.converter.Converter;

public class OrderDtoToEntityConverter implements Converter<OrderDto, Order> {

    @Override
    public Order convert(OrderDto source) {
        Order order = new Order();
        order.setDate(source.getDate());
        order.setGrade(source.getGrade());
        order.setTotalPrice(source.getTotalPrice());
        return order;
    }
}
