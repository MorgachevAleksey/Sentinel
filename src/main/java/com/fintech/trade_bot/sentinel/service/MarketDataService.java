package com.fintech.trade_bot.sentinel.service;

import com.fintech.trade_bot.sentinel.config.MarketDataProperties;
import com.fintech.trade_bot.sentinel.mapper.MoexCandlesMapper;
import com.fintech.trade_bot.sentinel.model.Candle;
import com.fintech.trade_bot.sentinel.provider.MarketDataProvider;
import com.fintech.trade_bot.sentinel.provider.MoexMarketDataProvider;
import com.fintech.trade_bot.sentinel.repository.CandleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

//Класс-оркестратор
@Service
public class MarketDataService {
    private final CandleRepository candleRepository;
    private final MarketDataProvider provider;

    public MarketDataService (CandleRepository candleRepository, MarketDataProvider provider){
        this.candleRepository = candleRepository;
        this.provider = provider;
    }

    @Transactional
    public int syncSize(
            String ticker,
            String timeframe,
            Instant start,
            Instant end
    ){
        List<Candle> candles = provider.fetchCandles(
                ticker,
                timeframe,
                start,
                end
        );
        candleRepository.saveAll(candles);

        return candles.size();
    }
}
