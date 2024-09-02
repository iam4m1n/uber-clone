package com.example.onlineTaxi.model.Users.User;


import com.example.onlineTaxi.model.driver.Driver;
import com.example.onlineTaxi.model.order.Order;
import com.example.onlineTaxi.model.payment.Payment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Long id;

    private String username;

    private String fullName;

    private String phoneNumber;

    private String password;

    private Integer age;

//    private ArrayList<Order> orders;
//    private ArrayList<Payment> payments;
//    private ArrayList<Driver> drivers;

}
