package com.SWOOSH.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "review_id")
    private Long reviewId;
    @OneToOne
    @JoinColumn(nullable = false, name = "car_wash_id")
    private CarWash carWash;
    @OneToOne
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    @Column(nullable = false, name = "review_text")
    private String reviewText;

}
