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
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@Slf4j
public class UserController {

    private final IUserService userService;
    private final IOrderService orderService;
    private final ConversionService conversionService;

    @PostMapping(value = "/createUser")
    //@PreAuthorize("hasAnyAuthority('ADMIN_PERMISSION')")
    public UserFullDto createUser(@RequestBody UserWithPasswordDto userDto) {
        User user = conversionService.convert(userDto, User.class);
        user = userService.createUser(user);
        log.debug("Created user: {}", user);
        return conversionService.convert(user, UserFullDto.class);
    }

    @PutMapping("/confirmation")
    public UserFullDto checkConfirmationCode(
            @RequestParam String code,
            @RequestBody @Valid UserDto userDto
    ) {

        User user = userService.checkConfirmationCode(userDto.getEmail(), code);
        if (user != null) {
            log.debug("Confirmation user: {}", user);
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

    @PostMapping("/createOrder")
    public OrderDto createOrder(
            @RequestParam String email,
            @RequestParam String location,
            @RequestParam List<String> services
    ) {

        Order order = orderService.createOrder(email, location, services);
        log.debug("Order created: {}", order);
        return conversionService.convert(order, OrderDto.class);
    }

    @PutMapping("/acceptOrder")
    public OrderDto acceptOrder(
            @RequestParam Long employeeId,
            @RequestBody @Valid OrderDto orderDto
    ) {
        Order order = conversionService.convert(orderDto, Order.class);
        order = orderService.acceptOrder(order, employeeId);
        log.debug("Order accepted: {}", order);
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
        log.debug("Graded order: {}, Created review {}", order, review);
        return conversionService.convert(order, OrderDto.class);
    }
}
