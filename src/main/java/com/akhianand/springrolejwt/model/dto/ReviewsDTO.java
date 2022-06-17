package com.akhianand.springrolejwt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ReviewsDTO {
    private int id;
    private String message;
    private int rating;
    private String email;
    private String senderEmail;
}
