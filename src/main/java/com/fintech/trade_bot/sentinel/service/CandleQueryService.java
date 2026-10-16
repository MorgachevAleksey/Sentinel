package com.fintech.trade_bot.sentinel.service;

import com.fintech.trade_bot.sentinel.model.Candle;
import com.fintech.trade_bot.sentinel.repository.CandleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.Instant;
import java.util.Optional;

@Service
public class CandleQueryService {
    private final static int MAX_PAGE_SIZE = 500;

    private final CandleRepository candleRepository;

    public CandleQueryService(CandleRepository candleRepository){
        this.candleRepository = candleRepository;
    }

    @Transactional(readOnly = true)
    public Page<Candle> findCandles(
            String ticker,
            String timeframe,
            Instant start,
            Instant end,
            int page,
            int size
    ){
        validateQuery(ticker, timeframe, start, end, page, size);

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.ASC, "openTime")
        );

        return candleRepository.findByTickerAndTimeframeAndOpenTimeBetween(ticker, timeframe, start, end, pageable);
    }

    private void validateQuery(
            String ticker,
            String timeframe,
            Instant start,
            Instant end,
            int page,
            int size
    ){
        if (ticker == null || ticker.isBlank()) {
            throw new IllegalArgumentException("Ticker must not be blank");
        }
        if (timeframe == null || timeframe.isBlank()) {
            throw new IllegalArgumentException("Timeframe must not be blank");
        }
        if (start == null) {
            throw new IllegalArgumentException("Start must not be null");
        }
        if (end == null) {
            throw new IllegalArgumentException("End must not be null");
        }
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("Start must not be after end");
        }
        if (page < 0) {
            throw new IllegalArgumentException("Page must not be negative");
        }
        if (size < 1 || size > MAX_PAGE_SIZE) {
            throw new IllegalArgumentException("Page size must be between 1 and " + MAX_PAGE_SIZE);
        }
    }

    public Optional<Candle> getLatestCandle(
            String ticker,
            String timeframe
    ){
        if (ticker == null || ticker.isBlank()) {
            throw new IllegalArgumentException("Ticker must not be blank");
        }
        if (timeframe == null || timeframe.isBlank()) {
            throw new IllegalArgumentException("Timeframe must not be blank");
        }
        return candleRepository.findTopByTickerAndTimeframeOrderByOpenTimeDesc(ticker, timeframe);
    }
}
