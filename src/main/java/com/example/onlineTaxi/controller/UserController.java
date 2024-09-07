package com.example.onlineTaxi.controller;


import com.example.onlineTaxi.service.impl.PaymentService;
import com.example.onlineTaxi.service.impl.TravelRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    // todo : crud for user


    private final TravelRequestService travelRequestService;

    private final PaymentService paymentService;

    @GetMapping("/balance")
    public String mojodi(@RequestParam Long id){
        return paymentService.showWallet(id);
    }




}
