package com.fintech.trade_bot.sentinel.repository;

import com.fintech.trade_bot.sentinel.model.Candle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CandleRepository extends JpaRepository<Candle, Long> {
    //Поиск всех ЗАПИСАННЫХ свечей за указанный период
    @Query("""
            select c.openTime
            from Candle c
            where c.ticker = :ticker
              and c.timeframe = :timeframe
              and c.openTime between :from and :to
            """)
    Set<Instant> findExistingOpenTimes(
            @Param("ticker") String ticker,
            @Param("timeframe") String timeframe,
            @Param("from") Instant from,
            @Param("to") Instant to
    );

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
