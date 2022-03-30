package com.SWOOSH.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "order_id")
    private Long orderId;
    @OneToOne
    @JoinColumn(nullable = false, name = "car_wash_id")
    private CarWash carWash;
    @OneToOne
    @JoinColumn(name = "employee_id")
    private User employee;
    @OneToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    @ManyToMany
    @JoinTable(
            name = "order_service",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Set<Service> orderedServices;
    @Column(nullable = false, name = "total_price")
    private int totalPrice;
    @Column(nullable = false, name = "date")
    private Date date;
    @Column(name = "grade")
    private double grade;

}
