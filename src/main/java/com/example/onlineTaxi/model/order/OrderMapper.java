package com.example.onlineTaxi.model.order;

import com.example.onlineTaxi.model.CustomPoint;
import com.example.onlineTaxi.model.Users.UserMapper;
import com.example.onlineTaxi.model.driver.DriverMapper;
import org.locationtech.jts.geom.Point;

public class OrderMapper {


    public static DriverMapper driverMapper;

    public static OrderDTO orderToOrderDto(OrderEntity order, Point userPoint, double distance, double cost, double timeMinutes){
        return OrderDTO
                .builder()
                .modifiedTime(order.getModifiedTime())
                .type(order.getType())
                .status(order.getStatus())
                .user(UserMapper.userToUserDto(order.getUser(), userPoint))
                .driver(DriverMapper.driverToDriverDto(order.getDriver()))
                .paymentMethod(order.getPayment())
                .startPoint(new CustomPoint(order.getStartPoint()))
                .endPoint(new CustomPoint(order.getEndPoint()))
                .userPoint(new CustomPoint(userPoint))
                .distance("Distance in kiloMeters: " + distance)
                .tripTime("trip time in minutes: " + timeMinutes)
                .cost(cost)
                .build();
    }


    public static OrderDTO orderToOrderDto(OrderEntity order) {
        return OrderDTO
                .builder()
                .modifiedTime(order.getModifiedTime())
                .type(order.getType())
                .status(order.getStatus())
                .user(UserMapper.userToUserDto(order.getUser()))
                .driver(DriverMapper.driverToDriverDto(order.getDriver()))
                .paymentMethod(order.getPayment())
                .startPoint(new CustomPoint(order.getStartPoint()))
                .endPoint(new CustomPoint(order.getEndPoint()))
                .build();

    }
}
