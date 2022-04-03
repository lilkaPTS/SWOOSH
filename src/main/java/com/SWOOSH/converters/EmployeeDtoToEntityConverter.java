package com.SWOOSH.converters;

import com.SWOOSH.dto.EmployeeDto;
import com.SWOOSH.model.Employee;
import org.springframework.core.convert.converter.Converter;

public class EmployeeDtoToEntityConverter implements Converter<EmployeeDto, Employee> {

    private final UserFullDtoToEntityConverter userFullDtoToEntityConverter = new UserFullDtoToEntityConverter();
    private final CarWashDtoToEntityConverter carWashDtoToEntityConverter = new CarWashDtoToEntityConverter();

    @Override
    public Employee convert(EmployeeDto source) {
        Employee employee = new Employee();
        employee.setId(source.getId());
        employee.setUser(userFullDtoToEntityConverter.convert(source.getUserDto()));
        employee.setCarWash(carWashDtoToEntityConverter.convert(source.getCarWashDto()));
        return employee;
    }
}