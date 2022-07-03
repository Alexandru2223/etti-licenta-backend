package com.akhianand.springrolejwt.repository;

import com.akhianand.springrolejwt.model.entity.CountJobsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface CountJobsRepository extends JpaRepository<CountJobsEntity, Long> {

    List<CountJobsEntity> findCountJobsEntityByDateBetween(LocalDateTime date1, LocalDateTime date2);
}
