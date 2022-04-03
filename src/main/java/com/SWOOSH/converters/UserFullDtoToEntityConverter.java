package com.SWOOSH.converters;

import com.SWOOSH.dto.UserFullDto;
import com.SWOOSH.model.User;
import org.springframework.core.convert.converter.Converter;

public class UserFullDtoToEntityConverter implements Converter<UserFullDto, User> {

    @Override
    public User convert(UserFullDto source) {
        User user = new User();
        user.setId(source.getId());
        user.setName(source.getName());
        user.setStatus(source.getStatus());
        user.setRole(source.getRole());
        user.setEmail(source.getEmail());
        return user;
    }
}
