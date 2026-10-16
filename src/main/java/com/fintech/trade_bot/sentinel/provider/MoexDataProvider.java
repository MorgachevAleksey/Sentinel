package com.fintech.trade_bot.sentinel.provider;

import com.fintech.trade_bot.sentinel.mapper.MoexCandlesMapper;
import org.springframework.stereotype.Component;

@Component
public class MoexDataProvider implements DataProvider {
    private final MoexCandlesMapper moexCandlesMapper;

    public MoexDataProvider(MoexCandlesMapper moexCandlesMapper){
        this.moexCandlesMapper = moexCandlesMapper;
    }
}
