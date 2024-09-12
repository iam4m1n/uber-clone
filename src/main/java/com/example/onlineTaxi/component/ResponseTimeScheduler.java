package com.example.onlineTaxi.component;

import com.example.onlineTaxi.filter.ResponseTimeInterceptor;
import com.example.onlineTaxi.microservices.MscService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResponseTimeScheduler {

    private static final Logger logger = LoggerFactory.getLogger(ResponseTimeScheduler.class);


    private final ResponseTimeInterceptor responseTimeInterceptor;

    private final MscService mscService;


    // todo : sent to a new microservice


    @Scheduled(fixedRate = 5 * 60 * 1000) // 5 minutes in milliseconds
    public void logAverageResponseTime() {
        double averageResponseTime = responseTimeInterceptor.getAverageResponseTime();
        mscService.sendTransaction("Average Response Time in the last 5 minutes: " + averageResponseTime +" ms");
        responseTimeInterceptor.clearResponseTimes();
        // cheara im khat ro comment kardadam???????????????????
    }
}
