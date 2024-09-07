package com.example.onlineTaxi.model.driver;

import com.example.onlineTaxi.model.CustomPoint;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriverDTO {

    private String username;

    private String phoneNumber;

    private String fullName;

    private CustomPoint location;



}
