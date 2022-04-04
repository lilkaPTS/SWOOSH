package com.SWOOSH.controller.protect;

import com.SWOOSH.dto.OrderDto;
import com.SWOOSH.dto.ReviewDto;
import com.SWOOSH.model.Order;
import com.SWOOSH.model.Review;
import com.SWOOSH.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final IOrderService orderService;
    private final ConversionService conversionService;

    @PostMapping("/createOrder")
    @PreAuthorize("hasAnyAuthority('CUSTOMER_PERMISSION')")
    public OrderDto createOrder(@RequestParam String email, @RequestParam String location, @RequestBody List<String> services) {
        Order order = orderService.createOrder(email, location, services);
        return conversionService.convert(order, OrderDto.class);
    }

    @PutMapping("/gradeOrder")
    @PreAuthorize("hasAnyAuthority('CUSTOMER_PERMISSION')")
    public OrderDto gradeOrder(@RequestParam Double grade, @RequestParam Long orderId, @RequestBody @Valid ReviewDto reviewDto) {
        Review review = conversionService.convert(reviewDto, Review.class);
        Order order = orderService.gradeOrder(orderId, grade, review);
        return conversionService.convert(order, OrderDto.class);
    }
}
