package com.fintech.trade_bot.sentinel.service;

import org.springframework.stereotype.Service;

@Service
public class HomeService {
    public String home(){
        return "Добро пожаловать в робота для торговли Sentinel" + " | " +
                "Актуальный список эндпоинтов: " +
                "http://localhost:8080/market/{ticker}/sync - загрузка дневных свечей с MOEX в БД" + " || " +
                "http://localhost:8080/info/health - статус приложения" + " | " +
                "http://localhost:8080/info/status - JSON со статусом и временем";
    }
}
