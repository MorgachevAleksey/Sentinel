package com.fintech.trade_bot.sentinel.provider;

import com.fintech.trade_bot.sentinel.mapper.MoexCandlesMapper;
import org.springframework.stereotype.Component;

@Component
public class MoexMarketDataProvider implements MarketDataProvider {
    private final MoexCandlesMapper moexCandlesMapper;

    public MoexMarketDataProvider(MoexCandlesMapper moexCandlesMapper){
        this.moexCandlesMapper = moexCandlesMapper;
    }
}
