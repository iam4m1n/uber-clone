package com.example.onlineTaxi.component;

import com.example.onlineTaxi.interceptor.ResponseTimeInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ResponseTimeScheduler {

    private static final Logger logger = LoggerFactory.getLogger(ResponseTimeScheduler.class);

    @Autowired
    private ResponseTimeInterceptor responseTimeInterceptor;

    @Scheduled(fixedRate = 5 * 60 * 1000) // 5 minutes in milliseconds
    public void logAverageResponseTime() {
        double averageResponseTime = responseTimeInterceptor.getAverageResponseTime();
        logger.info("Average Response Time in the last 5 minutes: {} ms", averageResponseTime);
        responseTimeInterceptor.clearResponseTimes();
    }
}
