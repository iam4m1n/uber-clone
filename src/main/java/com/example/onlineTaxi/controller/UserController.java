package com.example.onlineTaxi.controller;

import com.example.onlineTaxi.model.AuthenticationResponse;
import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    @PostMapping(path = "/register")
    public UserEntity register(@RequestBody UserEntity userEntity){
        userService.register(userEntity);
        return userEntity;
    }

    @PostMapping(path = "/login")
    public AuthenticationResponse login(@RequestBody UserEntity userEntity){
        return userService.verify(userEntity);
    }

    @GetMapping("all")
    public ResponseEntity<List<UserEntity>> getAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

}
