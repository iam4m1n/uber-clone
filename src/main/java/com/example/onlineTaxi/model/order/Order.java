package com.example.onlineTaxi.model.order;

import com.example.onlineTaxi.enums.TravelStatus;
import com.example.onlineTaxi.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    private Long Id;
    private Date modifiedTime;
    private VehicleType type;
    private TravelStatus status;




//    private Long userId;
//    private Long driverId;
//    private Point startPoint;
//    private Point endPoint;

}
