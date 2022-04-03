package com.SWOOSH.converters;

import com.SWOOSH.dto.CarWashDto;
import com.SWOOSH.model.CarWash;
import org.springframework.core.convert.converter.Converter;

public class CarWashToDtoConverter implements Converter<CarWash, CarWashDto> {

    @Override
    public CarWashDto convert(CarWash source) {
        CarWashDto carWashDto = new CarWashDto();
        carWashDto.setCarWashId(source.getCarWashId());
        carWashDto.setLocation(source.getLocation());
        carWashDto.setName(source.getName());
        carWashDto.setNumber(source.getNumber());
        return carWashDto;
    }
}