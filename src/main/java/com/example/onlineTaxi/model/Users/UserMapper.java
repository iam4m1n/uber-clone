package com.example.onlineTaxi.model.Users;

import com.example.onlineTaxi.model.CustomPoint;
import com.example.onlineTaxi.model.Users.User.UserDTO;
import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.order.OrderDTO;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserDTO userToUserDto(UserEntity userEntity, Point userFirstLocation){
        return UserDTO.builder()
                .username(userEntity.getUsername())
                .fullName(userEntity.getFullName())
                .phoneNumber(userEntity.getPhoneNumber())
                .userFirstLocation(new CustomPoint(userFirstLocation))
                .build();
    }



}
