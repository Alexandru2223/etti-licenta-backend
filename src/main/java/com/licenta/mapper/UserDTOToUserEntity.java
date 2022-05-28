package com.licenta.mapper;

import com.licenta.model.dto.UserDTO;
import com.licenta.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserDTOToUserEntity implements Converter<UserDTO, UserEntity> {
    @Override
    public UserEntity convert(UserDTO source) {
        return UserEntity.builder()
                .id(source.getId())
                .username(source.getUsername())
                .password("")
                .build();
    }
}
