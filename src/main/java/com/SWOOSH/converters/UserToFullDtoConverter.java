package com.SWOOSH.converters;

import com.SWOOSH.dto.UserFullDto;
import com.SWOOSH.model.User;
import org.springframework.core.convert.converter.Converter;

public class UserToFullDtoConverter implements Converter<User, UserFullDto> {

    @Override
    public UserFullDto convert(User source) {
        UserFullDto userDto = new UserFullDto();
        userDto.setId(source.getId());
        userDto.setName(source.getName());
        userDto.setStatus(source.getStatus());
        userDto.setRole(source.getRole());
        userDto.setEmail(source.getEmail());
        return userDto;
    }
}
