package com.fintech.trade_bot.sentinel.repository;

import com.fintech.trade_bot.sentinel.model.Candle;
import com.fintech.trade_bot.sentinel.service.CandleQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
@Transactional
public class CandleRepositoryTest {

    private final CandleRepository candleRepository;
    private final CandleQueryService candleQueryService;

    @Autowired
    CandleRepositoryTest(CandleRepository candleRepository,
                         CandleQueryService candleQueryService) {
        this.candleRepository = candleRepository;
        this.candleQueryService = candleQueryService;
    }

    @BeforeEach
    void clean() {
        candleRepository.deleteAll();
    }

    @Test
    void shouldReturnCandlesInChronologicalOrder() {
        Instant start = Instant.parse("2026-01-01T00:00:00Z");
        Instant middle = Instant.parse("2026-01-02T00:00:00Z");
        Instant end = Instant.parse("2026-01-03T00:00:00Z");

        candleRepository.saveAll(List.of(
                createCandle("SBER", "1d", middle),
                createCandle("SBER", "1d", start),
                createCandle("SBER", "1h", start),
                createCandle("GAZP", "1d", start)
        ));

        Page<Candle> result = candleQueryService.findCandles(
                "SBER", "1d", start, end, 0, 10);

        assertEquals(2, result.getTotalElements());
        assertEquals(start, result.getContent().get(0).getOpenTime());
        assertEquals(middle, result.getContent().get(1).getOpenTime());
    }

    @Test
    void shouldRejectInvalidDateRange() {
        Instant start = Instant.parse("2026-01-03T00:00:00Z");
        Instant end = Instant.parse("2026-01-01T00:00:00Z");

        assertThrows(IllegalArgumentException.class,
                () -> candleQueryService.findCandles(
                        "SBER", "1d", start, end, 0, 10));
    }

    @Test
    void shouldRejectOversizedPage() {
        Instant start = Instant.parse("2026-01-01T00:00:00Z");
        Instant end = Instant.parse("2026-01-03T00:00:00Z");

        assertThrows(IllegalArgumentException.class,
                () -> candleQueryService.findCandles(
                        "SBER", "1d", start, end, 0, 501));
    }

    @Test
    void shouldReturnLatestCandle() {
        Instant first = Instant.parse("2026-01-01T00:00:00Z");
        Instant second = Instant.parse("2026-01-02T00:00:00Z");
        Instant third = Instant.parse("2026-01-03T00:00:00Z");

        candleRepository.saveAll(List.of(
                createCandle("SBER", "1d", first),
                createCandle("SBER", "1d", second),
                createCandle("SBER", "1d", third)
        ));

        Optional<Candle> latest = candleQueryService.getLatestCandle("SBER", "1d");

        assertTrue(latest.isPresent());
        assertEquals(third, latest.get().getOpenTime());
    }

    private Candle createCandle(String ticker, String timeframe, Instant openTime) {
        return new Candle(ticker, timeframe, openTime,
                new BigDecimal("100.00"), // openPrice
                new BigDecimal("90.00"),  // lowPrice
                new BigDecimal("110.00"), // highPrice
                new BigDecimal("105.00"), // closePrice
                new BigDecimal("1000.00") // volume
        );
    }
}