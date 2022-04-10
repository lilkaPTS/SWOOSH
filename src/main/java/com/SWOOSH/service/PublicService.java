package com.SWOOSH.service;

import com.SWOOSH.dto.ReviewsDTO;
import com.SWOOSH.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PublicService {

    private final CarWashRepository carWashRepository;
    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;

    public List<ReviewsDTO> getReviews(String carWashLocation) {
        return reviewRepository.getReviewsByCarWash(carWashRepository.getCarWashByLocation(carWashLocation))
                .stream()
                .map(review -> new ReviewsDTO(review.getUser().getName(), carWashLocation, review.getReviewText()))
                .collect(Collectors.toList());
    }
}
