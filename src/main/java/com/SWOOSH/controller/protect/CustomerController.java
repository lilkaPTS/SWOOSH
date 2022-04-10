package com.SWOOSH.controller.protect;

import com.SWOOSH.dto.CreateOrderDTO;
import com.SWOOSH.model.Order;
import com.SWOOSH.model.Review;
import com.SWOOSH.repository.UserRepository;
import com.SWOOSH.service.OrderService;
import com.SWOOSH.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/createOrder")
    //@PreAuthorize("hasAnyAuthority('CUSTOMER_PERMISSION')")
    public Long createOrder(CreateOrderDTO createOrderDTO) {
        return orderService.createOrder(createOrderDTO);
    }

    @PutMapping("/gradeOrder")
    //@PreAuthorize("hasAnyAuthority('CUSTOMER_PERMISSION')")
    public boolean gradeOrder(Long orderId, Integer grade, String text) {
        return orderService.gradeOrder(orderId, grade, text);
    }

    @GetMapping("/getName")
    public String getName(String email) {
        return userService.getCustomerName(email);
    }
}
