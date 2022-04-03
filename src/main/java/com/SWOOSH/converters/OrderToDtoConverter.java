package com.SWOOSH.converters;

import com.SWOOSH.dto.OrderDto;
import com.SWOOSH.model.Order;
import org.springframework.core.convert.converter.Converter;

public class OrderToDtoConverter implements Converter<Order, OrderDto> {

    @Override
    public OrderDto convert(Order source) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(source.getOrderId());
        orderDto.setDate(source.getDate());
        orderDto.setGrade(source.getGrade());
        orderDto.setTotalPrice(source.getTotalPrice());
        return orderDto;
    }
}