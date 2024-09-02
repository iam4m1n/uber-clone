package com.example.onlineTaxi.model.driver;

import com.example.onlineTaxi.enums.DriverStatus;
import com.example.onlineTaxi.enums.VehicleType;
import com.example.onlineTaxi.model.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Driver {

    private Long Id;
    private String username;
    private String password;
    private String phoneNumber;
    private String fullName;
    private Integer age;
    private DriverStatus status;
    private Boolean isLicenseValid;
    private LocalDateTime licenseExpireTime;
    private Double rating;
    private Vehicle vehicle;

//    private Point currentLocation;

//    private ArrayList<Order> travelHistory;

}
