package com.fintech.trade_bot.sentinel.controller;

import com.fintech.trade_bot.sentinel.client.MoexClient;
import com.fintech.trade_bot.sentinel.dto.response.MoexCandlesResponse;
import com.fintech.trade_bot.sentinel.service.MarketDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarketDataController {
    private final MarketDataService marketDataService;

    public MarketDataController(MarketDataService marketDataService){
        this.marketDataService = marketDataService;
    }
}
