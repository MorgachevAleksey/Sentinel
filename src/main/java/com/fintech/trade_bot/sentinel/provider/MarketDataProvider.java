package com.fintech.trade_bot.sentinel.provider;

import com.fintech.trade_bot.sentinel.model.Candle;

import java.time.Instant;
import java.util.List;

public interface MarketDataProvider {
    List<Candle> fetchCandles(
            String ticker,
            String timeframe,
            Instant start,
            Instant end
    );
}
