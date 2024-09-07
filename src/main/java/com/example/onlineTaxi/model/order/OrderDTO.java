package com.example.onlineTaxi.model.order;

import com.example.onlineTaxi.enums.TravelStatus;
import com.example.onlineTaxi.enums.VehicleType;
import com.example.onlineTaxi.model.CustomPoint;
import com.example.onlineTaxi.model.Users.User.UserDTO;
import com.example.onlineTaxi.model.driver.DriverDTO;
import com.example.onlineTaxi.model.payment.Payment;
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
public class OrderDTO {

    private Date modifiedTime;

    private VehicleType type;

    private TravelStatus status;

    private UserDTO user;

    private DriverDTO driver;

    private Payment paymentMethod;

    private CustomPoint startPoint;

    private CustomPoint endPoint;

    private CustomPoint userPoint;



    private String tripTime;

    private String distance;

    private double cost;

}
