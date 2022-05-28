package com.licenta.service;

import com.licenta.mapper.JobsDTOToJobsEntity;
import com.licenta.mapper.JobsEntityToJobsDTO;
import com.licenta.mapper.UserEntityToUserDTO;
import com.licenta.model.dto.JobsDTO;
import com.licenta.model.dto.UserDTO;
import com.licenta.model.entity.JobsEntity;
import com.licenta.model.entity.UserEntity;
import com.licenta.repository.JobsRepository;
import com.licenta.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobService {

    private final JobsDTOToJobsEntity jobsDTOToJobsEntity;

    private final JobsEntityToJobsDTO jobsEntityToJobsDTO;

    private final UserEntityToUserDTO userEntityToUserDTO;

    private final JobsRepository repository;

    private final UserRepository userRepository;

    public void create(JobsDTO jobsDTO, String user) {
        UserEntity userEntity = userRepository.findByUsername(user);
        UserDTO userDTO = userEntityToUserDTO.convert(userEntity);
        jobsDTO.setUserDTO(userDTO);
        System.out.println(jobsDTO);
        JobsEntity jobsEntity = jobsDTOToJobsEntity.convert(jobsDTO);

        repository.save(jobsEntity);
    }

    public List<JobsDTO> getJobsByUser(String user) {
        System.out.println(user);
        List<JobsEntity> jobsByUser = repository.getJobsByUser(user);
        List<JobsDTO> collect = jobsByUser.stream().map(jobsEntityToJobsDTO::convert).collect(Collectors.toList());
        return collect;
    }
}
