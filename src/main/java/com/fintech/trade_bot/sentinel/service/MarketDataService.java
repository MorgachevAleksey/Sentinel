package com.fintech.trade_bot.sentinel.service;

import com.fintech.trade_bot.sentinel.model.Candle;
import com.fintech.trade_bot.sentinel.provider.MarketDataProvider;
import com.fintech.trade_bot.sentinel.repository.CandleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        List<Candle> fetched = provider.fetchCandles(
                ticker,
                timeframe,
                start,
                end
        );

        if (fetched.isEmpty()){
            return 0;
        }

        Instant minOpenTime = fetched.stream()
                .map(Candle::getOpenTime)
                .min(Instant::compareTo)
                .orElse(start);

        Instant maxOpenTime = fetched.stream()
                .map(Candle::getOpenTime)
                .max(Instant::compareTo)
                .orElse(end);

        Set<Instant> existing = candleRepository.findExistingOpenTimes(
                ticker,
                timeframe,
                minOpenTime,
                maxOpenTime
        );

        List<Candle> newCandles = fetched.stream().filter(
                c -> !existing.contains(c.getOpenTime())
        ).toList();

        candleRepository.saveAll(newCandles);

        return newCandles.size();
    }
}
