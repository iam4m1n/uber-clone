package com.example.onlineTaxi.service.impl;


import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.driver.Driver;
import com.example.onlineTaxi.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final RedisTemplate<String, Object> redisTemplate;

    private final DriverRepository driverRepository;

    private final OrderService orderService;


    public Driver getDriverById(Long id){

        String cacheKey = "Driver: " + id;


        try{
            Driver cachedDriver = (Driver) redisTemplate.opsForValue().get(cacheKey);
            if (cachedDriver != null) {
                return cachedDriver;
            }


        } catch ( Exception e){

            redisTemplate.delete(cacheKey);


        }


        Driver driver = driverRepository.findById(id).orElse(null);
        if (driver != null) {
            redisTemplate.opsForValue().set(cacheKey, driver);
        }

        return driver;






    }

    // Fetch a list of all drivers
    public List<Driver> getAllDrivers(UserEntity user,
                                      double userLat,
                                      double userLon,
                                      double startLat,
                                      double startLon,
                                      double endLat,
                                      double endLon) {
        String cacheKey = "DriverList";

        try {
            // Retrieve the cached list of drivers
            List<Driver> cachedDrivers = (List<Driver>) redisTemplate.opsForValue().get(cacheKey);
            if (cachedDrivers != null && !cachedDrivers.isEmpty()) {
                return cachedDrivers;
            }
        } catch (Exception e) {
            redisTemplate.delete(cacheKey);
        }

        // If not cached, fetch from the repository
        List<Driver> drivers = orderService.getAllDrivers(new UserEntity(), userLat, userLon, startLat, startLon, endLat, endLon);
        if (!drivers.isEmpty()) {
            // Cache the list of drivers
            redisTemplate.opsForValue().set(cacheKey, drivers);
        }

        return drivers;
    }



}
