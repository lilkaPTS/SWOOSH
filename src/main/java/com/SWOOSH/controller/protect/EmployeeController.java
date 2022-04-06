package com.SWOOSH.controller.protect;

import com.SWOOSH.dto.OrderDto;
import com.SWOOSH.model.Order;
import com.SWOOSH.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final IOrderService orderService;
    private final ConversionService conversionService;

    @PutMapping("/acceptOrder")
    //@PreAuthorize("hasAnyAuthority('EMPLOYEE_PERMISSION')")
    public OrderDto acceptOrder(@RequestParam Long employeeId, @RequestBody @Valid OrderDto orderDto) {
        Order order = conversionService.convert(orderDto, Order.class);
        order = orderService.acceptOrder(order, employeeId);
        return conversionService.convert(order, OrderDto.class);
    }
}
