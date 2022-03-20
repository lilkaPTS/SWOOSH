package com.SWOOSH.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "employee_id")
    private int employeeId;
    @ManyToOne
    @JoinColumn(name = "car_wash_id", nullable = false)
    private CarWash carWash;
    @Column(nullable = false)
    private String name;
    @Column(name = "passport_data", nullable = false)
    private String passportData;

    public Employee(CarWash carWash,  String name, String passportData) {
        this.carWash = carWash;
        this.name = name;
        this.passportData = passportData;
    }
}
