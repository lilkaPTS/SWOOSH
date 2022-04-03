package com.SWOOSH.converters;

import com.SWOOSH.dto.UserWithPasswordDto;
import com.SWOOSH.model.User;
import org.springframework.core.convert.converter.Converter;

public class UserToPasswordDtoConverter implements Converter<User, UserWithPasswordDto> {

    @Override
    public UserWithPasswordDto convert(User source) {
        UserWithPasswordDto userDto = new UserWithPasswordDto();
        userDto.setId(source.getId());
        userDto.setName(source.getName());
        userDto.setStatus(source.getStatus());
        userDto.setRole(source.getRole());
        userDto.setEmail(source.getEmail());
        userDto.setPassword(source.getPassword());
        return userDto;
    }
}
