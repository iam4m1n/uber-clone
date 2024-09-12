package com.example.onlineTaxi.controller;

import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.order.OrderDTO;
import com.example.onlineTaxi.model.order.OrderEntity;
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
@RequestMapping("/order")
public class OrderController {


    private final UserServiceImpl userService;

    private final OrderService orderService;

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

        return orderService.createOrderForUser(user, userLat, userLon, startLat, startLon, endLat, endLon, type);
    }



    @PostMapping("/delete")
    public ResponseEntity<OrderEntity> delete(@RequestParam Long orderId){
        orderService.deleteTravel(orderId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get")
    public ResponseEntity<OrderDTO> getById(@RequestParam Long orderId){
        return new ResponseEntity<>(orderService.getById(orderId), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<OrderEntity>> getById(){
        return new ResponseEntity<>(orderService.getAllById(), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<OrderEntity> update(@RequestParam Long orderId,
                                              @RequestParam double discount,
                                              @RequestParam String discountCode
    ){
        return new ResponseEntity<>(orderService.update(orderId, discount, discountCode), HttpStatus.OK);
    }



}
