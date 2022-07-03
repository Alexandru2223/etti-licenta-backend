package com.akhianand.springrolejwt.repository;

import com.akhianand.springrolejwt.model.entity.ReviewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewsRepository extends JpaRepository<ReviewsEntity, Long> {

    @Query("SELECT r from ReviewsEntity r where r.userEntity.username = :username")
    List<ReviewsEntity> getReviewsEntitiesByAuthor(String username);

    @Query("SELECT r from ReviewsEntity r where r.email= :username")
    List<ReviewsEntity> getReviewsForUser(String username);
}
