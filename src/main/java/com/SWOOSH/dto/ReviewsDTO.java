package com.SWOOSH.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewsDTO {
    private String username;
    private String carWashLocation;
    private String text;
}
