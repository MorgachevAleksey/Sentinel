package com.fintech.trade_bot.sentinel.service;

import org.springframework.stereotype.Service;

@Service
public class HomeService {
    public String home(){
        return "Добро пожаловать в робота для торговли Sentinel" + "\n" +
                "Актуальный список эндопинтов" + "\n" +
                "/market/{ticker} - получение информации по конкретному тикеру" + "\n" +
                "/info/health" + "\n" +
                "/info/status";
    }
}
