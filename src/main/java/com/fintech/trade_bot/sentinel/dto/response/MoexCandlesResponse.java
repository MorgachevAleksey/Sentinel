package com.fintech.trade_bot.sentinel.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MoexCandlesResponse (Candles candles){
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Candles(
            List<String> columns,
            List<List<Object>> data
    ){}
};