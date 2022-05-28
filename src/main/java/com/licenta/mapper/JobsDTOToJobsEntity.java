package com.licenta.mapper;

import com.licenta.model.dto.JobsDTO;
import com.licenta.model.dto.UserDTO;
import com.licenta.model.entity.JobsEntity;
import com.licenta.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JobsDTOToJobsEntity implements Converter<JobsDTO, JobsEntity> {

    private final UserDTOToUserEntity userDTOToUserEntity;

    @Override
    public JobsEntity convert(JobsDTO source) {
        return JobsEntity.builder().
                id(source.getId())
                .description(source.getDescription())
                .location(source.getLocation())
                .price(source.getPrice())
                .title(source.getTitle())
                .phone(source.getPhone())
                .image1(source.getImage1())
                .image2(source.getImage2())
                .image3(source.getImage3())
                .image4(source.getImage4())
                .userEntity(mapUser(source.getUserDTO()))
                .build();
    }

    private UserEntity mapUser(UserDTO userDTO) {
        return userDTOToUserEntity.convert(userDTO);
    }
}
