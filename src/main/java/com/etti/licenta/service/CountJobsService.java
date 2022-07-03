package com.akhianand.springrolejwt.service;

import com.akhianand.springrolejwt.model.entity.CountJobsEntity;
import com.akhianand.springrolejwt.repository.CountJobsRepository;
import com.akhianand.springrolejwt.repository.JobsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CountJobsService {

    private final CountJobsRepository countJobsRepository;

    private final JobsRepository jobsRepository;

    public void save(LocalDateTime localDateTime){
        CountJobsEntity countJobsEntity = CountJobsEntity.builder()
                .number(jobsRepository.getCountJobs())
                .date(localDateTime).build();
        countJobsRepository.save(countJobsEntity);
    }

    public List<CountJobsEntity> getLastWeek(){
        return countJobsRepository.findCountJobsEntityByDateBetween(LocalDateTime.now().minusDays(6), LocalDateTime.now());
    }
}
