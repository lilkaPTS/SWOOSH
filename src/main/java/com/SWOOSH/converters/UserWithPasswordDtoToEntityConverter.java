package com.SWOOSH.converters;

import com.SWOOSH.dto.UserWithPasswordDto;
import com.SWOOSH.model.User;
import org.springframework.core.convert.converter.Converter;

public class UserWithPasswordDtoToEntityConverter implements Converter<UserWithPasswordDto, User> {

    @Override
    public User convert(UserWithPasswordDto source) {
        User user = new User();
        user.setId(source.getId());
        user.setName(source.getName());
        user.setStatus(source.getStatus());
        user.setRole(source.getRole());
        user.setEmail(source.getEmail());
        user.setPassword(source.getPassword());
        return user;
    }
}
