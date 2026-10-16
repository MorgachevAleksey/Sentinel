package com.fintech.trade_bot.sentinel.repository;

import com.fintech.trade_bot.sentinel.model.Candle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface CandleRepository extends JpaRepository<Candle, Long> {
    //Поиск всех свечей по тикеру и таймфрейму за указанный временной диапазон
    Page<Candle> findByTickerAndTimeframeAndOpenTimeBetween(
            String ticker,
            String timeframe,
            Instant start,
            Instant end,
            Pageable pageable
    );

    //Поиск последней свечи по тикеру и таймфрейму (Optuinal - потому что свечи может и не быть)
    Optional<Candle> findTopByTickerAndTimeframeOrderByOpenTimeDesc(
            String ticker,
            String timeframe
    );
}
