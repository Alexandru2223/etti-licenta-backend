package com.akhianand.springrolejwt.mapper;

import com.akhianand.springrolejwt.model.UserDto;
import com.akhianand.springrolejwt.model.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDTOToUserEntity implements Converter<UserDto, UserEntity> {
    @Override
    public UserEntity convert(UserDto source) {
        return UserEntity.builder()
                .username(source.getUsername())
                .password(source.getPassword())
                .businessTitle(source.getBusinessTitle())
                .phone(source.getPhone())
                .birthdate(source.getBirthdate())
                .name(source.getName())
                .build();
    }
}
