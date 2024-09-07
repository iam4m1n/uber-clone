package com.example.onlineTaxi.controller;

import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.driver.Driver;
import com.example.onlineTaxi.model.order.OrderDTO;
import com.example.onlineTaxi.model.order.OrderEntity;
import com.example.onlineTaxi.model.payment.Payment;
import com.example.onlineTaxi.model.payment.PaymentDTO;
import com.example.onlineTaxi.service.impl.PaymentService;
import com.example.onlineTaxi.service.impl.TravelRequestService;
import com.example.onlineTaxi.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {


    private final UserServiceImpl userService;

    private final TravelRequestService travelRequestService;

    private final PaymentService paymentService;

    @PostMapping("/create")
    public OrderDTO createOrder(@RequestParam Long userId,
                                @RequestParam double userLat,
                                @RequestParam double userLon,
                                @RequestParam double startLat,
                                @RequestParam double startLon,
                                @RequestParam double endLat,
                                @RequestParam double endLon,
                                @RequestParam String type
    ) {
        // Fetch user entity based on userId
        UserEntity user = userService.findById(userId);

        return travelRequestService.createOrderForUser(user, userLat, userLon, startLat, startLon, endLat, endLon, type);
    }



    @PostMapping("/delete")
    public void delete(@RequestParam Long orderId){
        travelRequestService.deleteTravel(orderId);
    }



}
