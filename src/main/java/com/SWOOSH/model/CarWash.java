package com.SWOOSH.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "car_washes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarWash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "car_wash_id")
    private int carWashId;

    @OneToMany(mappedBy = "carWash")
    private Set<Service> services;

    @OneToMany(mappedBy = "carWash")
    private Set<Employee> employees;

    @Column(nullable = false)
    private String location;


}
