package com.akhianand.springrolejwt.controller;

import com.akhianand.springrolejwt.mapper.JobsEntityToJobsDTO;
import com.akhianand.springrolejwt.model.UserEntity;
import com.akhianand.springrolejwt.model.dto.JobsDTO;
import com.akhianand.springrolejwt.model.dto.JobsSaveDto;
import com.akhianand.springrolejwt.model.entity.CountJobsEntity;
import com.akhianand.springrolejwt.model.entity.JobsEntity;
import com.akhianand.springrolejwt.model.entity.JobsSaveEntity;
import com.akhianand.springrolejwt.repository.CountJobsRepository;
import com.akhianand.springrolejwt.repository.JobsRepository;
import com.akhianand.springrolejwt.repository.JobsSaveRepository;
import com.akhianand.springrolejwt.repository.UserRepository;
import com.akhianand.springrolejwt.service.CountJobsService;
import com.akhianand.springrolejwt.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class JobsController {
    private final JobsRepository jobsRepository;

    private final JobsEntityToJobsDTO jobsEntityToJobsDTO;

    private final CountJobsService countJobsService;

    private final JobService service;

    private final UserRepository userRepository;

    private final JobsSaveRepository jobsSaveRepository;

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/jobs"})
    @ResponseStatus(HttpStatus.OK)
    public List<JobsDTO> getAllJobs() {
        return jobsRepository.getAllJobs().stream()
                .map(jobsEntityToJobsDTO::convert)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/jobs/count"})
    @ResponseStatus(HttpStatus.OK)
    public List<CountJobsEntity> getLastWeekCount(){
        return countJobsService.getLastWeek();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/jobs/sort/{value}"})
    @ResponseStatus(HttpStatus.OK)
    public List<JobsDTO> getAllJobsSorted(@PathVariable String value) {
        switch (value) {
            case "priceasc":
                return jobsRepository.getAllJobs().stream()
                        .sorted(Comparator.comparingLong(JobsEntity::getPrice))
                        .map(jobsEntityToJobsDTO::convert)
                        .collect(Collectors.toList());
            case "pricedesc":
                return jobsRepository.getAllJobs().stream()
                        .sorted(Comparator.comparingLong(JobsEntity::getPrice).reversed())
                        .map(jobsEntityToJobsDTO::convert)
                        .collect(Collectors.toList());
            case "abcasc":
                return jobsRepository.getAllJobs().stream()
                        .sorted(Comparator.comparing(JobsEntity::getTitle))
                        .map(jobsEntityToJobsDTO::convert)
                        .collect(Collectors.toList());
            case "abcdesc":
                return jobsRepository.getAllJobs().stream()
                        .sorted(Comparator.comparing(JobsEntity::getTitle).reversed())
                        .map(jobsEntityToJobsDTO::convert)
                        .collect(Collectors.toList());

            default:
                return null;
        }

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"/jobs/id/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public JobsDTO getJobById(@PathVariable Long id) {
        System.out.println(id);
        return jobsEntityToJobsDTO.convert(jobsRepository.findById(id).get());
    }

    @GetMapping({"/jobs/{keyword}"})
    @ResponseStatus(HttpStatus.OK)
    public List<JobsDTO> getAllJobs(@PathVariable String keyword) {
        return jobsRepository.getJobsByKeyword(keyword).stream()
                .map(jobsEntityToJobsDTO::convert)
                .collect(Collectors.toList());
    }

    @GetMapping({"/jobs/{keyword}/sort/{value}"})
    @ResponseStatus(HttpStatus.OK)
    public List<JobsDTO> getAllJobsByKeywordSorted(@PathVariable String keyword, @PathVariable String value) {
        return jobsRepository.getJobsByKeyword(keyword).stream()
                .sorted(Comparator.comparingLong(JobsEntity::getPrice))
                .map(jobsEntityToJobsDTO::convert)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping({"/jobs/create/{user}"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createJob(@RequestBody JobsSaveDto jobsDTO, @PathVariable String user) {
        service.create(jobsDTO, user);
    }

    @GetMapping({"/jobs/user/{user}"})
    @ResponseStatus(HttpStatus.OK)
    public List<JobsDTO> getJobsByUser(@PathVariable String user) {
        return service.getJobsByUser(user);
    }

    @GetMapping({"/jobs/user/count/{user}"})
    @ResponseStatus(HttpStatus.OK)
    public long countJobsByUser(@PathVariable String user) {
        return service.getJobsByUser(user).stream().count();
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping({"/jobs/delete/{user}/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public List<JobsDTO> countJobsByUser(@PathVariable String user, @PathVariable long id) {
        JobsEntity jobsEntity = jobsRepository.findById(id).get();

        if (jobsEntity.getUserEntity().getUsername().toLowerCase().equals(user.toLowerCase())){
            jobsRepository.deleteById(id);
        }
        return getJobsByUser(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping({"/jobs/delete/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteJobByAdmin(@PathVariable long id) {
        jobsRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping({"/jobs/update/{user}/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void updateUserJob(@RequestBody JobsSaveEntity jobsEntity, @PathVariable String user, @PathVariable long id) {
        JobsEntity jobs = jobsRepository.findById(id).get();
        jobs.setTitle(jobsEntity.getTitle());
        jobs.setDescription(jobsEntity.getDescription());
        jobs.setPrice(jobsEntity.getPrice());
        jobs.setPhone(jobsEntity.getPhone());
        System.out.println(jobs.getUserEntity().getUsername().equalsIgnoreCase(user));
        if (jobs.getUserEntity().getUsername().equalsIgnoreCase(user)){
            jobsRepository.save(jobs);
        }
    }



}
