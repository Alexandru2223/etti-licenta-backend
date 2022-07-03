package com.akhianand.springrolejwt.service;

import com.akhianand.springrolejwt.mapper.JobsDTOToJobsEntity;
import com.akhianand.springrolejwt.mapper.JobsEntityToJobsDTO;
import com.akhianand.springrolejwt.mapper.JobsSaveDtoToJobsSaveEntity;
import com.akhianand.springrolejwt.mapper.UserEntityToUserDTO;
import com.akhianand.springrolejwt.model.UserDto;
import com.akhianand.springrolejwt.model.UserEntity;
import com.akhianand.springrolejwt.model.dto.JobsDTO;
import com.akhianand.springrolejwt.model.dto.JobsSaveDto;
import com.akhianand.springrolejwt.model.entity.JobsEntity;
import com.akhianand.springrolejwt.model.entity.JobsSaveEntity;
import com.akhianand.springrolejwt.repository.JobsRepository;
import com.akhianand.springrolejwt.repository.JobsSaveRepository;
import com.akhianand.springrolejwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobService {

    private final JobsSaveDtoToJobsSaveEntity jobsSaveDtoToJobsSaveEntity;

    private final JobsEntityToJobsDTO jobsEntityToJobsDTO;

    private final UserEntityToUserDTO userEntityToUserDTO;

    private final JobsRepository repository;

    private final JobsSaveRepository jobsSaveRepository;

    private final UserRepository userRepository;

    public void create(JobsSaveDto jobsDTO, String user) {
        UserEntity userEntity = userRepository.findByUsername(user);
        UserDto userDTO = userEntityToUserDTO.convert(userEntity);
        jobsDTO.setUserId(userDTO.getId());
        JobsSaveEntity jobsEntity = jobsSaveDtoToJobsSaveEntity.convert(jobsDTO);
        jobsSaveRepository.save(jobsEntity);
    }

    public List<JobsDTO> getJobsByUser(String user) {
        System.out.println(user);
        List<JobsEntity> jobsByUser = repository.getJobsByUser(user);
        List<JobsDTO> collect = jobsByUser.stream().map(jobsEntityToJobsDTO::convert).collect(Collectors.toList());
        return collect;
    }
}
