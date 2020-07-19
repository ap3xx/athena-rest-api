package com.ap3x.ifoodtest.controllers;

import com.ap3x.ifoodtest.dto.GenericResponse;
import com.ap3x.ifoodtest.services.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("metrics")
public class MetricsController {

    @Autowired
    private MetricsService service;

    @GetMapping("ordersByState/{date}")
    public GenericResponse getOrdersByState(@PathVariable String date) throws SQLException {
        return new GenericResponse(service.getOrdersByState(date));
    }

    @GetMapping("topRestaurants/{customerId}")
    public GenericResponse getTopRestaurants(@PathVariable Integer customerId) throws SQLException {
        return new GenericResponse(service.getTopRestaurants(customerId));
    }

}
