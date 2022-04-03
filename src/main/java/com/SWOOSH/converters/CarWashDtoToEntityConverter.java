package com.SWOOSH.converters;

import com.SWOOSH.dto.CarWashDto;
import com.SWOOSH.model.CarWash;
import org.springframework.core.convert.converter.Converter;

public class CarWashDtoToEntityConverter implements Converter<CarWashDto, CarWash> {

    @Override
    public CarWash convert(CarWashDto source) {
        CarWash carWash = new CarWash();
        carWash.setCarWashId(source.getCarWashId());
        carWash.setLocation(source.getLocation());
        carWash.setName(source.getName());
        carWash.setNumber(source.getNumber());
        return carWash;
    }
}