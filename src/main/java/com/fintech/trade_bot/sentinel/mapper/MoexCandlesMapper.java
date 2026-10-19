package com.fintech.trade_bot.sentinel.mapper;

import com.fintech.trade_bot.sentinel.dto.response.MoexCandlesResponse;
import com.fintech.trade_bot.sentinel.model.Candle;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MoexCandlesMapper {
    private static final DateTimeFormatter MOEX_DT =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final ZoneId MOEX_ZONE = ZoneId.of("Europe/Moscow");

    public List<Candle> toCandles(
            String ticker,
            String timeframe,
            MoexCandlesResponse response
    ){
        MoexCandlesResponse.Candles candles = response.candles();
        if (candles == null || candles.data() == null || candles.data().isEmpty()) {
            return List.of();
        }

        Map<String, Integer> index = new HashMap<>();
        for (int i = 0; i < candles.columns().size(); i++){
            index.put(candles.columns().get(i), i);
        }

        List<Candle> result = new ArrayList<>(candles.data().size());

        for (List<Object> row : candles.data()){
            result.add(mapRow(ticker, timeframe, row, index));
        }

        return result;
    }

    private Candle mapRow(
            String ticker,
            String timeframe,
            List<Object> row,
            Map<String, Integer> index
    ){
        BigDecimal open = toBigDecimal(row.get(index.get("open")));
        BigDecimal close = toBigDecimal(row.get(index.get("close")));
        BigDecimal high = toBigDecimal(row.get(index.get("high")));
        BigDecimal low = toBigDecimal(row.get(index.get("low")));
        BigDecimal volume = toBigDecimal(row.get(index.get("volume")));

        String begin = row.get(index.get("begin")).toString();
        LocalDateTime localDateTime = LocalDateTime.parse(begin, MOEX_DT);
        Instant openTime = localDateTime.atZone(MOEX_ZONE).toInstant();

        return new Candle(
                ticker,
                timeframe,
                openTime,
                open,
                low,
                high,
                close,
                volume
        );
    }

    private BigDecimal toBigDecimal(Object value){
        return new BigDecimal(value.toString());
    }
}
