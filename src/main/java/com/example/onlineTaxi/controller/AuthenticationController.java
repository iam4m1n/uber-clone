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
    public ResponseEntity<UserEntity> register(@RequestBody UserEntity userEntity){
        return new ResponseEntity<>(userService.register(userEntity), HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestParam String username, @RequestParam String password){
        return new ResponseEntity<>(userService.verify(username, password), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        userService.refreshToken(request, response);
    }

}
