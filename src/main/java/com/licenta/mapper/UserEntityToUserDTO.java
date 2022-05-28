package com.licenta.mapper;

import com.licenta.model.dto.UserDTO;
import com.licenta.model.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToUserDTO implements Converter<UserEntity, UserDTO> {
    @Override
    public UserDTO convert(UserEntity source) {
        return UserDTO.builder()
                .id(source.getId())
                .username(source.getUsername())
                .password("")
                .build();
    }
}
