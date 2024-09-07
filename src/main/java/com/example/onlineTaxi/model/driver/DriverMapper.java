package com.example.onlineTaxi.model.driver;

import com.example.onlineTaxi.model.CustomPoint;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Location;
import org.springframework.stereotype.Component;

import java.security.PublicKey;


@Component
public class DriverMapper {

    public static DriverDTO driverToDriverDto(Driver driver){
        return DriverDTO
                .builder()
                .username(driver.getUsername())
                .fullName(driver.getFullName())
                .phoneNumber(driver.getPhoneNumber())
                .location(new CustomPoint(driver.getLocation()))
                .build();
    }


    public static Driver driverDtoToDriver(DriverDTO driverDTO){
        return Driver
                .builder()
                .username(driverDTO.getUsername())
                .fullName(driverDTO.getFullName())
                .phoneNumber(driverDTO.getPhoneNumber())
                .build();
    }


}
