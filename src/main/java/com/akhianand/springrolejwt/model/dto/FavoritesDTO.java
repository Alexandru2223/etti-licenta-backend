package com.akhianand.springrolejwt.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class FavoritesDTO {

    private long jobId;
    private long userId;
}
