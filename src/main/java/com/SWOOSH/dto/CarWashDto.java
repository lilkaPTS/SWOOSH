package com.SWOOSH.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarWashDto {

    private Long carWashId;
    private String location;
    private String number;
    private String name;
}
