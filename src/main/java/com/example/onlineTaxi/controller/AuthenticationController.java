package com.example.onlineTaxi.controller;

import com.example.onlineTaxi.model.AuthenticationResponse;
import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class AuthenticationController {

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

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        userService.refreshToken(request, response);
    }

    @GetMapping("all")
    public ResponseEntity<List<UserEntity>> getAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }






}
