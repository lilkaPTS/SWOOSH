package com.SWOOSH.converters;

import com.SWOOSH.dto.UserDto;
import com.SWOOSH.model.User;
import org.springframework.core.convert.converter.Converter;

public class UserToDtoConverter implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User source) {
        UserDto userDto = new UserDto();
        userDto.setId(source.getId());
        userDto.setEmail(source.getEmail());
        return userDto;
    }
}
