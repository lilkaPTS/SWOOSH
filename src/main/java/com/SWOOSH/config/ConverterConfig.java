package com.SWOOSH.config;

import com.SWOOSH.converters.CarWashDtoToEntityConverter;
import com.SWOOSH.converters.CarWashToDtoConverter;
import com.SWOOSH.converters.EmployeeDtoToEntityConverter;
import com.SWOOSH.converters.EmployeeToDtoConverter;
import com.SWOOSH.converters.OrderDtoToEntityConverter;
import com.SWOOSH.converters.OrderToDtoConverter;
import com.SWOOSH.converters.ReviewDtoToEntityConverter;
import com.SWOOSH.converters.ReviewToDtoConverter;
import com.SWOOSH.converters.ServiceDtoToEntityConverter;
import com.SWOOSH.converters.ServiceToDtoConverter;
import com.SWOOSH.converters.UserDtoToEntityConverter;
import com.SWOOSH.converters.UserFullDtoToEntityConverter;
import com.SWOOSH.converters.UserToDtoConverter;
import com.SWOOSH.converters.UserToFullDtoConverter;
import com.SWOOSH.converters.UserToPasswordDtoConverter;
import com.SWOOSH.converters.UserWithPasswordDtoToEntityConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConverterConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new UserToDtoConverter());
        registry.addConverter(new UserToFullDtoConverter());
        registry.addConverter(new UserToPasswordDtoConverter());
        registry.addConverter(new UserDtoToEntityConverter());
        registry.addConverter(new UserFullDtoToEntityConverter());
        registry.addConverter(new UserWithPasswordDtoToEntityConverter());
        registry.addConverter(new CarWashToDtoConverter());
        registry.addConverter(new CarWashDtoToEntityConverter());
        registry.addConverter(new EmployeeDtoToEntityConverter());
        registry.addConverter(new EmployeeToDtoConverter());
        registry.addConverter(new OrderToDtoConverter());
        registry.addConverter(new OrderDtoToEntityConverter());
        registry.addConverter(new ReviewToDtoConverter());
        registry.addConverter(new ReviewDtoToEntityConverter());
        registry.addConverter(new ServiceToDtoConverter());
        registry.addConverter(new ServiceDtoToEntityConverter());
    }
}
