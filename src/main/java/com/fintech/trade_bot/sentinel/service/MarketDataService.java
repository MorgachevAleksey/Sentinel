package com.fintech.trade_bot.sentinel.service;

import com.fintech.trade_bot.sentinel.config.MarketDataProperties;
import org.springframework.stereotype.Service;

@Service
public class MarketDataService {
    private final MarketDataProperties marketDataProperties;

    public MarketDataService(MarketDataProperties marketDataProperties){
        this.marketDataProperties = marketDataProperties;
    }

    public String getTickerInfo(String ticker){
        return  "Ticker: " + ticker +
                " BaseUrl= " + marketDataProperties.getBaseUrl() +
                " TimeoutSeconds= " + marketDataProperties.getTimeoutSeconds() +
                " MaxAttempts= " + marketDataProperties.getRetry().getMaxAttempts() +
                " BackoffMs= " + marketDataProperties.getRetry().getBackoffMs();
    }
}
