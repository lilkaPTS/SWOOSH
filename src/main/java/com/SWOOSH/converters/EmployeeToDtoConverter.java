package com.SWOOSH.converters;

import com.SWOOSH.dto.EmployeeDto;
import com.SWOOSH.model.Employee;
import org.springframework.core.convert.converter.Converter;

public class EmployeeToDtoConverter implements Converter<Employee, EmployeeDto> {

    private final UserToFullDtoConverter userToFullDtoConverter = new UserToFullDtoConverter();
    private final CarWashToDtoConverter carWashToDtoConverter = new CarWashToDtoConverter();

    @Override
    public EmployeeDto convert(Employee source) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(source.getId());
        employeeDto.setUserDto(userToFullDtoConverter.convert(source.getUser()));
        employeeDto.setCarWashDto(carWashToDtoConverter.convert(source.getCarWash()));
        return employeeDto;
    }
}