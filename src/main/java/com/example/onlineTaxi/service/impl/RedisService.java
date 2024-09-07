package com.example.onlineTaxi.service.impl;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

    private RedissonClient redissonClient;

    public void saveToRedis(String key, String value) {
        RMap<String, String> map = redissonClient.getMap("myMap");
        map.put(key, value);
    }

    public String getFromRedis(String key) {
        RMap<String, String> map = redissonClient.getMap("myMap");
        return map.get(key);
    }
}
