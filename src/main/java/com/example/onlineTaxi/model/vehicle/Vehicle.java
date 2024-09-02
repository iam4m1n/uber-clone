package com.example.onlineTaxi.model.vehicle;

import com.example.onlineTaxi.enums.Colors;
import com.example.onlineTaxi.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehicle {

    // Vehicle make (e.g., Toyota, Honda)
    private String company;

    // Vehicle model (e.g., Camry, Accord)
    private String model;

    // Vehicle year
    private int year;

    // Vehicle color
    private Colors color;

    private VehicleType vehicleType;
}
