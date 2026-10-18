package com.fintech.trade_bot.sentinel.provider;

import com.fintech.trade_bot.sentinel.client.MoexClient;
import com.fintech.trade_bot.sentinel.mapper.MoexCandlesMapper;
import com.fintech.trade_bot.sentinel.model.Candle;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class MoexMarketDataProvider implements MarketDataProvider {
    private final MoexClient moexClient;
    private final MoexCandlesMapper moexCandlesMapper;

    public MoexMarketDataProvider(MoexClient moexClient, MoexCandlesMapper moexCandlesMapper){
        this.moexClient = moexClient;
        this.moexCandlesMapper = moexCandlesMapper;
    }

    public List<Candle> fetchCandles(
            String ticker,
            String timeframe,
            Instant start,
            Instant end
    ){
        return null;
    };
}
