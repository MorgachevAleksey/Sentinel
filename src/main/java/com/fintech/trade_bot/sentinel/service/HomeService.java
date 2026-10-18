package com.fintech.trade_bot.sentinel.service;

import org.springframework.stereotype.Service;

@Service
public class HomeService {
    public String home(){
        return "Добро пожаловать в робота для торговли Sentinel" + " | " +
                "Актуальный список эндпоинтов: " + " | " +
                "POST /market/{ticker}/sync — загрузка свечей с MOEX";
    }
}
