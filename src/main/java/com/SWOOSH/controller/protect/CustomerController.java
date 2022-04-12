package com.SWOOSH.controller.protect;

import com.SWOOSH.dto.CreateOrderDTO;
import com.SWOOSH.dto.ServiceDTO;
import com.SWOOSH.service.OrderService;
import com.SWOOSH.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Long createOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        return orderService.createOrder(createOrderDTO);
    }

    @PutMapping("/createReview")
    //@PreAuthorize("hasAnyAuthority('CUSTOMER_PERMISSION')")
    public boolean gradeOrder(Long orderId, Integer grade, String text) {
        return orderService.gradeOrder(orderId, grade, text);
    }

    @GetMapping("/getName")
    //@PreAuthorize("hasAnyAuthority('CUSTOMER_PERMISSION')")
    public String getName(String email) {
        return userService.getCustomerName(email);
    }

    @GetMapping("/getListOfServices")
    //@PreAuthorize("hasAnyAuthority('CUSTOMER_PERMISSION')")
    public List<ServiceDTO> getListOfServices(String carWashLocation) {
        return userService.getListOfServices(carWashLocation);
    }
}
