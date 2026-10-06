package com.fintech.trade_bot.sentinel.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class HealthService {
    public String health(){
        return "Sentinel is running!";
    }

    public Map<String, Object> status(){
        return Map.of(
                "status", "UP",
                "timestamp", LocalDateTime.now().toString(),
                "service", "Sentinel"
        );
    }
}
