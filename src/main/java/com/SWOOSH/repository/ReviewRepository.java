package com.SWOOSH.repository;

import com.SWOOSH.model.CarWash;
import com.SWOOSH.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> getReviewsByCarWash(CarWash carWash);
}
