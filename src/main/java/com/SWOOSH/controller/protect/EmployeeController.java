package com.SWOOSH.controller.protect;

import com.SWOOSH.model.Order;
import com.SWOOSH.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final OrderService orderService;
    private final ConversionService conversionService;

//    @PutMapping("/acceptOrder")
//    //@PreAuthorize("hasAnyAuthority('EMPLOYEE_PERMISSION')")
//    public OrderDto acceptOrder(@RequestParam Long employeeId, @RequestBody @Valid OrderDto orderDto) {
//        Order order = conversionService.convert(orderDto, Order.class);
//        order = orderService.acceptOrder(order, employeeId);
//        return conversionService.convert(order, OrderDto.class);
//    }
}
