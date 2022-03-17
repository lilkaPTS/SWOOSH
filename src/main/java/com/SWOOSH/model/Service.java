package com.SWOOSH.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "services")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "service_id")
    private int serviceId;
    @ManyToOne
    @JoinColumn(name = "car_wash_id", nullable = false)
    private CarWash carWash;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer price;
}
