package com.example.onlineTaxi.model.User.normalUser;


import lombok.Data;


@Data
public class NormalUser {

    private Long id;

    private String username;

    private String fullName;

    private String phoneNumber;

    private String password;

    private Integer age;

}
