package com.SWOOSH.converters;

import com.SWOOSH.dto.UserDto;
import com.SWOOSH.model.User;
import org.springframework.core.convert.converter.Converter;

public class UserDtoToEntityConverter implements Converter<UserDto, User> {

    @Override
    public User convert(UserDto source) {
        User user = new User();
        user.setId(source.getId());
        user.setEmail(source.getEmail());
        return user;
    }
}
