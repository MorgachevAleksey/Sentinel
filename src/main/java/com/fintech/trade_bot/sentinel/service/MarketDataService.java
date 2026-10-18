package com.fintech.trade_bot.sentinel.service;

import com.fintech.trade_bot.sentinel.config.MarketDataProperties;
import com.fintech.trade_bot.sentinel.mapper.MoexCandlesMapper;
import com.fintech.trade_bot.sentinel.provider.MoexMarketDataProvider;
import com.fintech.trade_bot.sentinel.repository.CandleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

//Класс-оркестратор
@Service
public class MarketDataService {
    private final CandleRepository candleRepository;
    private final MoexMarketDataProvider provider;

    public MarketDataService (CandleRepository candleRepository, MoexMarketDataProvider provider){
        this.candleRepository = candleRepository;
        this.provider = provider;
    }

    @Transactional
    public int syncSize(String ticker,
                        String timeframe,
                        Instant from,
                        Instant to
    ){
        return 0;
    }
}
