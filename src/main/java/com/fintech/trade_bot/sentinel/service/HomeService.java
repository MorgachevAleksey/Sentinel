package com.fintech.trade_bot.sentinel.service;

import org.springframework.stereotype.Service;

@Service
public class HomeService {
    public String home(){
        return "Добро пожаловать в робота для торговли Sentinel" + " | " +
                "Актуальный список эндопинтов: " + " | " +
                "/market - получение данных в виде свечей" + " | " +
                "/info - мониторинг приложения";
    }
}
