package com.akhianand.springrolejwt.model.dto;

import com.akhianand.springrolejwt.model.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class JobsDTO {

    private long id;

    private String title;

    private String description;

    private long price;

    private String location;

    private String phone;

    private String image1;

    private String image2;

    private String image3;

    private String image4;

    private String category;

    private UserDto userDTO;
}
