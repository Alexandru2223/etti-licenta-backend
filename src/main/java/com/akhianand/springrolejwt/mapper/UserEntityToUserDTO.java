package com.akhianand.springrolejwt.mapper;

import com.akhianand.springrolejwt.model.UserDto;
import com.akhianand.springrolejwt.model.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToUserDTO implements Converter<UserEntity, UserDto> {
    @Override
    public UserDto convert(UserEntity source) {
        return UserDto.builder()
                .id(source.getId())
                .username(source.getUsername())
                .password(source.getPassword())
                .businessTitle(source.getBusinessTitle())
                .phone(source.getPhone())
                .birthdate(source.getBirthdate())
                .name(source.getName())
                .build();
    }
}
