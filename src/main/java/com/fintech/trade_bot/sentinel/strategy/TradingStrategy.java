package com.fintech.trade_bot.sentinel.strategy;

import com.fintech.trade_bot.sentinel.model.Candle;

import java.util.List;

public interface TradingStrategy {
    public String name();

    public TradingSignal evaluate(List<Candle> candles);
}
