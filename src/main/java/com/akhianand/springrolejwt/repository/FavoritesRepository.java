package com.akhianand.springrolejwt.repository;

import com.akhianand.springrolejwt.model.entity.FavoritesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritesRepository extends JpaRepository<FavoritesEntity, Long> {

    @Query("SELECT f from FavoritesEntity f where f.userEntity.username =:user")
    List<FavoritesEntity> getAllByUser(String user);

    @Query("SELECT f from FavoritesEntity f where f.jobsEntity.id =:id")
    FavoritesEntity getByJobId(Long id);
}
