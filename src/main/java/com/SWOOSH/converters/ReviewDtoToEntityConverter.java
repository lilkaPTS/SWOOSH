package com.SWOOSH.converters;

import com.SWOOSH.dto.ReviewDto;
import com.SWOOSH.model.Review;
import org.springframework.core.convert.converter.Converter;

public class ReviewDtoToEntityConverter implements Converter<ReviewDto, Review> {

    @Override
    public Review convert(ReviewDto source) {
        Review review = new Review();
        review.setReviewId(source.getReviewId());
        review.setReviewText(source.getReviewText());
        return review;
    }
}
