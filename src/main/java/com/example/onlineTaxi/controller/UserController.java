package com.example.onlineTaxi.controller;


import com.example.onlineTaxi.model.Users.User.UserDTO;
import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.service.impl.PaymentService;
import com.example.onlineTaxi.service.impl.OrderService;
import com.example.onlineTaxi.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    // todo : crud for user


    private final OrderService orderService;

    private final UserServiceImpl userService;

    private final PaymentService paymentService;

    @GetMapping("/balance")
    public String mojodi(@RequestParam Long id){
        return paymentService.showWallet(id);
    }


    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAll(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<UserDTO> getUserById(@RequestParam Long userId){
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUserById(@RequestParam Long userId){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
