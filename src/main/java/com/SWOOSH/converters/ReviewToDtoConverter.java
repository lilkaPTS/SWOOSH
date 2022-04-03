package com.SWOOSH.converters;

import com.SWOOSH.dto.ReviewDto;
import com.SWOOSH.model.Review;
import org.springframework.core.convert.converter.Converter;

public class ReviewToDtoConverter implements Converter<Review, ReviewDto> {

    @Override
    public ReviewDto convert(Review source) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReviewId(source.getReviewId());
        reviewDto.setReviewText(source.getReviewText());
        return reviewDto;
    }
}