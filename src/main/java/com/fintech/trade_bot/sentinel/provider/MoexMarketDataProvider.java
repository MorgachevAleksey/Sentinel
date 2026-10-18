package com.fintech.trade_bot.sentinel.provider;

import com.fintech.trade_bot.sentinel.client.MoexClient;
import com.fintech.trade_bot.sentinel.dto.response.MoexCandlesResponse;
import com.fintech.trade_bot.sentinel.mapper.MoexCandlesMapper;
import com.fintech.trade_bot.sentinel.model.Candle;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;

@Component
public class MoexMarketDataProvider implements MarketDataProvider {
    private final MoexClient moexClient;
    private final MoexCandlesMapper moexCandlesMapper;

    public MoexMarketDataProvider(MoexClient moexClient, MoexCandlesMapper moexCandlesMapper){
        this.moexClient = moexClient;
        this.moexCandlesMapper = moexCandlesMapper;
    }

    @Override
    public List<Candle> fetchCandles(
            String ticker,
            String timeframe,
            Instant start,
            Instant end
    ){
        MoexCandlesResponse response = moexClient.fetchDailyCandles(ticker, start.atZone(ZoneOffset.UTC).toLocalDate());
        return moexCandlesMapper.toCandles(ticker, timeframe, response);
    };
}
