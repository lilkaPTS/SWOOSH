package com.SWOOSH.converters;

import com.SWOOSH.dto.ServiceDto;
import com.SWOOSH.model.Service;
import org.springframework.core.convert.converter.Converter;

public class ServiceDtoToEntityConverter implements Converter<ServiceDto, Service> {

    @Override
    public Service convert(ServiceDto source) {
        Service service = new Service();
        service.setServiceId(source.getServiceId());
        service.setName(source.getName());
        service.setPrice(source.getPrice());
        return service;
    }
}

