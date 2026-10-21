package com.fintech.trade_bot.sentinel.controller;

import com.fintech.trade_bot.sentinel.dto.response.CandleResponse;
import com.fintech.trade_bot.sentinel.model.Candle;
import com.fintech.trade_bot.sentinel.service.CandleQueryService;
import com.fintech.trade_bot.sentinel.service.MarketDataService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@RestController
@RequestMapping("/market")
public class MarketDataController {
    private final MarketDataService marketDataService;
    private final CandleQueryService candleQueryService;

    public MarketDataController(MarketDataService marketDataService, CandleQueryService candleQueryService){
        this.marketDataService = marketDataService;
        this.candleQueryService = candleQueryService;
    }

    @PostMapping("/{ticker}/sync")
    public Map<String, Object> sync(
            @PathVariable String ticker,
            @RequestParam(defaultValue = "1d") String timeframe,
            @RequestParam(required = false) String from,
            @RequestParam(required = false) String to
    ){
        //По умочанию - если не передано время, последние 30 дневных
        Instant fromInstant = (from == null)
                ? Instant.now().minus(30, ChronoUnit.DAYS)
                : Instant.parse(from);
        //По умолчанию - до последней
        Instant toInstant = (to == null)
                ? Instant.now()
                : Instant.parse(to);

        int saved = marketDataService.syncSize(
                ticker,
                timeframe,
                fromInstant,
                toInstant
        );

        return Map.of(
                "ticker", ticker,
                "saved", saved,
                "timeframe", timeframe,
                "from", String.valueOf(from),
                "to", String.valueOf(to)
        );
    }
}
