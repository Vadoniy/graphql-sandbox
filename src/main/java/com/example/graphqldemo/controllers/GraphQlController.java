package com.example.graphqldemo.controllers;

import com.example.graphqldemo.domain.Vehicle;
import com.example.graphqldemo.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;

@Controller
@RequiredArgsConstructor
public class GraphQlController {

    private final VehicleService vehicleService;

    @QueryMapping
    public Collection<Vehicle> vehicles(@Argument Integer count) {
        return vehicleService.getVehicles(count);
    }

    @QueryMapping
    public Vehicle vehicle(@Argument Long id) {
        return vehicleService.getVehicle(id);
    }

    @MutationMapping
    public Vehicle createVehicle(@Argument String type, @Argument String modelCode, @Argument String brandName, @Argument String launchDate) {
        return vehicleService.createVehicle(type, modelCode, brandName, launchDate);
    }
}
