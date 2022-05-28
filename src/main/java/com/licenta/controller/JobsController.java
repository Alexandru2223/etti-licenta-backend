package com.licenta.controller;

import com.licenta.mapper.JobsEntityToJobsDTO;
import com.licenta.model.dto.JobsDTO;
import com.licenta.model.entity.JobsEntity;
import com.licenta.repository.JobsRepository;
import com.licenta.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class JobsController {
    private final JobsRepository jobsRepository;

    private final JobsEntityToJobsDTO jobsEntityToJobsDTO;

    private final JobService service;



    @GetMapping({"/jobs"})
    @ResponseStatus(HttpStatus.OK)
    public List<JobsDTO> getAllJobs(){
        return jobsRepository.getAllJobs().stream()
                .map(jobsEntityToJobsDTO::convert)
                .collect(Collectors.toList());
    }
    @GetMapping({"/jobs/{keyword}"})
    @ResponseStatus(HttpStatus.OK)
    public List<JobsDTO> getAllJobs(@PathVariable String keyword){
        return jobsRepository.getJobsByKeyword(keyword).stream()
                .map(jobsEntityToJobsDTO::convert)
                .collect(Collectors.toList());
    }

    @PostMapping({"/jobs/create/{user}"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createJob(@RequestBody JobsDTO jobsDTO, @PathVariable String user){
        service.create(jobsDTO, user);

    }

    @GetMapping({"/jobs/user/{user}"})
    @ResponseStatus(HttpStatus.OK)
    public List<JobsDTO> getJobsByUser(@PathVariable String user){
        return service.getJobsByUser(user);
    }
}
