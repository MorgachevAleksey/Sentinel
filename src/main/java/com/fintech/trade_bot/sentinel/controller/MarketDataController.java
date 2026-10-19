package com.fintech.trade_bot.sentinel.controller;

import com.fintech.trade_bot.sentinel.service.MarketDataService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MarketDataController {
    private final MarketDataService marketDataService;

    public MarketDataController(MarketDataService marketDataService){
        this.marketDataService = marketDataService;
    }

    @PostMapping("/market/{ticker}/sync")
    public Map<String, Object> sync(){
        return null;
    }
}
