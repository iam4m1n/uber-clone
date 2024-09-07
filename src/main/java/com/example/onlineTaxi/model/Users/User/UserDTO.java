package com.example.onlineTaxi.model.Users.User;

import com.example.onlineTaxi.model.CustomPoint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String username;
    private String fullName;
    private String phoneNumber;
    private CustomPoint userFirstLocation;



}
