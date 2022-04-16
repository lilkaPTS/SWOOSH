package com.SWOOSH.controller.protect;

import com.SWOOSH.dto.ReviewsDTO;
import com.SWOOSH.dto.UserStatsDTO;
import com.SWOOSH.service.PublicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class PublicController {

    private final PublicService publicController;

    @GetMapping("/getReviews")
    public List<ReviewsDTO> getReviews(String carWashLocation) {
        return publicController.getReviews(carWashLocation);
    }
}
