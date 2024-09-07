package com.example.onlineTaxi.model.vehicle;

import com.example.onlineTaxi.enums.Colors;
import com.example.onlineTaxi.enums.VehicleType;
import com.example.onlineTaxi.model.driver.Driver;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Vehicle make (e.g., Toyota, Honda)
    private String company;

    // Vehicle model (e.g., Camry, Accord)
    private String model;

    // Vehicle year
    private int year;

    // Vehicle color
    @Enumerated(EnumType.STRING)
    private Colors color;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;


    // One-to-One with Driver (optional back-reference)
    @OneToOne(mappedBy = "vehicle")
    private Driver driver;
}
