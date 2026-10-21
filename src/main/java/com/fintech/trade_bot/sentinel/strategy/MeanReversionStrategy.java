package com.fintech.trade_bot.sentinel.strategy;

import com.fintech.trade_bot.sentinel.model.Candle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeanReversionStrategy implements TradingStrategy{
    private static final String NAME = "Mean Reversion";

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public TradingSignal evaluate(List<Candle> candles) {
        return null;
    }
}
