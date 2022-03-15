package com.SWOOSH.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "car_washes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarWash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "user_id")
    private int carWashId;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
}
