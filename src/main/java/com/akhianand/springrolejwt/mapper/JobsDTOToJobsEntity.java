package com.akhianand.springrolejwt.mapper;

import com.akhianand.springrolejwt.model.UserDto;
import com.akhianand.springrolejwt.model.UserEntity;
import com.akhianand.springrolejwt.model.dto.JobsDTO;
import com.akhianand.springrolejwt.model.entity.JobsEntity;
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
                .category(source.getCategory())
                .userEntity(mapUser(source.getUserDTO()))
                .build();
    }

    private UserEntity mapUser(UserDto userDTO) {
        return userDTOToUserEntity.convert(userDTO);
    }
}
