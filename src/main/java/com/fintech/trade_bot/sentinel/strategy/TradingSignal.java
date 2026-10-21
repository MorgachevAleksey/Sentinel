package com.fintech.trade_bot.sentinel.strategy;

import java.math.BigDecimal;
import java.time.Instant;

public record TradingSignal(
        SignalType type
        //String ticker,
        //String timeframe,
        //BigDecimal price,
        //Instant timestamp,
        //String reason
){
}
