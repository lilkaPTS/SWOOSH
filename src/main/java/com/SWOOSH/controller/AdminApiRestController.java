package com.SWOOSH.controller;

import com.SWOOSH.model.Service;
import com.SWOOSH.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminApiRestController {

    @Autowired
    private ServiceRepository serviceRepository;

    @PostMapping("/addService")
    public Service addService(Service service) {
        return serviceRepository.save(service);
    }
}
