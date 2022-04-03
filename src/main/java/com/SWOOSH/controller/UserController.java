package com.SWOOSH.controller;

import com.SWOOSH.dto.OrderDto;
import com.SWOOSH.dto.ReviewDto;
import com.SWOOSH.dto.UserDto;
import com.SWOOSH.dto.UserFullDto;
import com.SWOOSH.dto.UserWithPasswordDto;
import com.SWOOSH.model.Order;
import com.SWOOSH.model.Review;
import com.SWOOSH.model.User;
import com.SWOOSH.service.IOrderService;
import com.SWOOSH.service.IUserService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;
    private final IOrderService orderService;
    private final ConversionService conversionService;

    @PostMapping(value = "/createUser")
    public UserFullDto createUser(@RequestBody UserWithPasswordDto userDto) {
        User user = conversionService.convert(userDto, User.class);
        user = userService.createUser(user);
        return conversionService.convert(user, UserFullDto.class);
    }

    @PutMapping("/checkConfirmation")
    public UserFullDto checkConfirmationCode(String code, String email) {
        User user = userService.checkConfirmationCode(email, code);
        if (user != null) {
            return conversionService.convert(user, UserFullDto.class);
        } else {
            try {
                throw new Exception("Incorrect code");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @PostMapping("/sendConfirmation")
    public void sendConfirmationCode(String email) {
        userService.sendConfirmationCode(email);
    }


    @PostMapping("/createOrder")
    public OrderDto createOrder(
            @RequestParam String email,
            @RequestParam String location,
            @RequestBody List<String> services
    ) {

        Order order = orderService.createOrder(email, location, services);
        return conversionService.convert(order, OrderDto.class);
    }

    @PutMapping("/acceptOrder")
    @PreAuthorize("hasAnyAuthority('EMPLOYEE_PERMISSION')")
    public OrderDto acceptOrder(
            @RequestParam Long employeeId,
            @RequestBody @Valid OrderDto orderDto
    ) {
        Order order = conversionService.convert(orderDto, Order.class);
        order = orderService.acceptOrder(order, employeeId);
        return conversionService.convert(order, OrderDto.class);
    }

    @PutMapping("/gradeOrder")
    public OrderDto gradeOrder(
            @RequestParam Double grade,
            @RequestParam Long orderId,
            @RequestBody @Valid ReviewDto reviewDto
    ) {
        Review review = conversionService.convert(reviewDto, Review.class);
        Order order = orderService.gradeOrder(orderId, grade, review);
        return conversionService.convert(order, OrderDto.class);
    }
}
