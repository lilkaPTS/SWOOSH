package com.SWOOSH.convertors;

import com.SWOOSH.dto.ServiceDTO;
import com.SWOOSH.model.Service;

public class Convertor {
    public static ServiceDTO serviceToServiceDTO(Service service){
        return new ServiceDTO(service.getName(), service.getPrice(), service.getCarWash().getLocation());
    }
}
