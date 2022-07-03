package com.akhianand.springrolejwt.mapper;

import com.akhianand.springrolejwt.model.dto.FavoritesDTO;
import com.akhianand.springrolejwt.model.entity.FavoritesEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FavoritesEntityToFavoritesDTOMapper implements Converter<FavoritesEntity, FavoritesDTO> {
    @Override
    public FavoritesDTO convert(FavoritesEntity favoritesEntity) {
        return FavoritesDTO.builder()
                .jobId(favoritesEntity.getJobsEntity().getId())
                .userId(favoritesEntity.getUserEntity().getId())
                .build();
    }
}
