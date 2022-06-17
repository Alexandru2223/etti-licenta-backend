package com.akhianand.springrolejwt.repository;

import com.akhianand.springrolejwt.controller.JobsController;
import com.akhianand.springrolejwt.model.dto.JobsSaveDto;
import com.akhianand.springrolejwt.model.entity.JobsSaveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface JobsSaveRepository extends JpaRepository<JobsSaveEntity, Long> {
}
