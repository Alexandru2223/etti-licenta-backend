package com.akhianand.springrolejwt.mapper;

import com.akhianand.springrolejwt.model.dto.JobsSaveDto;
import com.akhianand.springrolejwt.model.entity.JobsSaveEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class JobsSaveDtoToJobsSaveEntity implements Converter<JobsSaveDto, JobsSaveEntity> {
    @Override
    public JobsSaveEntity convert(JobsSaveDto source) {
        return JobsSaveEntity.builder().
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
                .userId(source.getUserId())
                .build();
    }
}
