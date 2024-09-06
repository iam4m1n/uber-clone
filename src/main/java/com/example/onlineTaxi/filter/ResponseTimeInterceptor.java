package com.example.onlineTaxi.filter;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class ResponseTimeInterceptor implements HandlerInterceptor {

    // Thread-safe list to store response times
    private static final List<Long> responseTimes = new CopyOnWriteArrayList<>();

    private static final ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler
    ) {
        // Store the start time before the request is processed
        startTimeThreadLocal.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull Object handler,
            Exception ex
    ) {
        // Calculate response time
        long startTime = startTimeThreadLocal.get();
        long endTime = System.currentTimeMillis();
        long responseTime = endTime - startTime;

        // Add the response time to the list
        responseTimes.add(responseTime);

        // Clean up the thread-local storage
        startTimeThreadLocal.remove();
    }

    // Method to calculate the average response time
    public double getAverageResponseTime() {
        if (responseTimes.isEmpty()) {
            return 0;
        }
        long totalResponseTime = responseTimes.stream().mapToLong(Long::longValue).sum();
        return totalResponseTime / (double) responseTimes.size();
    }

    // Method to clear the list of response times
    public void clearResponseTimes() {
        responseTimes.clear();
    }
}
