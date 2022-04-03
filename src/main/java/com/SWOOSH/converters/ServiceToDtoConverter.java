package com.SWOOSH.converters;

import com.SWOOSH.dto.ServiceDto;
import com.SWOOSH.model.Service;
import org.springframework.core.convert.converter.Converter;

public class ServiceToDtoConverter implements Converter<Service, ServiceDto> {

    @Override
    public ServiceDto convert(Service source) {
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setServiceId(source.getServiceId());
        serviceDto.setName(source.getName());
        serviceDto.setPrice(source.getPrice());
        return serviceDto;
    }
}
