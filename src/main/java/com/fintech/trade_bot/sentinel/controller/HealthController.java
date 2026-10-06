package com.fintech.trade_bot.sentinel.controller;

import com.fintech.trade_bot.sentinel.service.HealthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class HealthController {
    private final HealthService healthService;

    public HealthController(HealthService healthService){
        this.healthService = healthService;
    }

    @GetMapping("/health")
    public String health(){
        return healthService.health();
    }

    @GetMapping("/status")
    public Map<String, Object> status(){
        return healthService.status();
    }
}
