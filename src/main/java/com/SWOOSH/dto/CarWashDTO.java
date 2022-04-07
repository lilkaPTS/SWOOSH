package com.SWOOSH.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarWashDTO {
    private String location;
    private String name;
    private String number;
    private List<RegistrationDTO> employees;
    private List<ServiceDTO> services;
}
