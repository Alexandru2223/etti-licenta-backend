package com.akhianand.springrolejwt.mapper;

import com.akhianand.springrolejwt.model.dto.ReviewsDTO;
import com.akhianand.springrolejwt.model.entity.ReviewsEntity;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReviewEntityToReviewDtoMapper implements Converter<ReviewsEntity, ReviewsDTO> {
    @Override
    public ReviewsDTO convert(ReviewsEntity source) {
        return ReviewsDTO.builder()
                .id(source.getRating())
                .email(source.getEmail())
                .message(source.getMessage())
                .rating(source.getRating())
                .senderEmail(source.getUserEntity().getUsername())
                .build();
    }
}
