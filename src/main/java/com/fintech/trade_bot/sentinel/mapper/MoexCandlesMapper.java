package com.fintech.trade_bot.sentinel.mapper;

import com.fintech.trade_bot.sentinel.dto.response.MoexCandlesResponse;
import com.fintech.trade_bot.sentinel.model.Candle;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class MoexCandlesMapper {
    public List<Candle> toCandles(String ticker, String timeframe, MoexCandlesResponse response){
        private static final DateTimeFormatter MOEX_DT =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        private static final ZoneId MOEX_ZONE = ZoneId.of("Europe/Moscow");


    }
}
