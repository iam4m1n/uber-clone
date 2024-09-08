package com.example.onlineTaxi.controller;


import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.driver.Driver;
import com.example.onlineTaxi.service.impl.DriverService;
import com.example.onlineTaxi.service.impl.RedisService;
import com.example.onlineTaxi.service.impl.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DriverController {

    // todo : crud for driver

    private final RedisService redisService;

    private final OrderService orderService;

    private final DriverService driverService;

    @GetMapping("all-in-range")
    public ResponseEntity<List<Driver>> allInRange(@RequestParam Long userId,
                                                   @RequestParam double userLat,
                                                   @RequestParam double userLon,
                                                   @RequestParam double startLat,
                                                   @RequestParam double startLon,
                                                   @RequestParam double endLat,
                                                   @RequestParam double endLon) {
        List<Driver> drivers = driverService.getAllDrivers(new UserEntity(), userLat, userLon, startLat, startLon, endLat, endLon);

        if (drivers != null){
            return ResponseEntity.ok(drivers);
        }else {
            return ResponseEntity.notFound().build();
        }
    }







}
