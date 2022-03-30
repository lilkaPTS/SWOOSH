package com.SWOOSH.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car_washes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarWash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "car_wash_id")
    private Long carWashId;
    @OneToMany(mappedBy = "carWash")
    private List<Service> services;
    @OneToMany(mappedBy = "carWash")
    private List<Employee> employee;
    @Column(nullable = false)
    private String location;


}
