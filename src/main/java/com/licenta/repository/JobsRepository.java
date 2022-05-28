package com.licenta.repository;

import com.licenta.model.entity.JobsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobsRepository extends JpaRepository<JobsEntity, Long> {

    @Query("SELECT j FROM JobsEntity j inner join j.userEntity")
    List<JobsEntity> getAllJobs();

    @Query("SELECT j FROM JobsEntity j inner join j.userEntity where j.title LIKE %:keyword%")
    List<JobsEntity> getJobsByKeyword(@Param("keyword") String keyword);

    @Query("SELECT j FROM JobsEntity j inner join j.userEntity u where u.username =:user")
    List<JobsEntity> getJobsByUser(@Param("user") String user);
}
