package com.example.graphqldemo.service;

import com.example.graphqldemo.domain.Vehicle;
import com.example.graphqldemo.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public Collection<Vehicle> getVehicles(Integer limit) {
        final var vehiclesStream = vehicleRepository.findAll().stream();
        Stream<Vehicle> vehicleStream = limit != null ? vehiclesStream.limit(limit) : vehiclesStream;
        return vehicleStream.collect(Collectors.toList());
    }

    public Vehicle getVehicle(long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    public Vehicle createVehicle(String type, String modelCode, String brandName, String launchDate) {
        final var vehicle = new Vehicle();
        vehicle.setType(type);
        vehicle.setModelCode(modelCode);
        vehicle.setBrandName(brandName);
        Optional.ofNullable(launchDate)
                .map(LocalDate::parse)
                .ifPresent(vehicle::setLaunchDate);
        return vehicleRepository.save(vehicle);
    }
}
