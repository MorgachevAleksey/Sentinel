package com.fintech.trade_bot.sentinel.client;

import com.fintech.trade_bot.sentinel.dto.response.MoexCandlesResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;

@Component
public class MoexClient {
    private static final String CANDLES_PATH = "/iss/engines/stock/markets/shares/boards/TQBR/securities/{ticker}/candles.json";

    //Инжект Рест_Клиента! не конфига! Клиент уже создан бином в конфиге и готов к использованию
    private final RestClient moexRestClient;

    public MoexClient(RestClient moexRestClient){
        this.moexRestClient = moexRestClient;
    }

    public MoexCandlesResponse fetchDailyCandles(String ticker, LocalDate from){
        return moexRestClient.get() //Выбор Http-метода
                .uri(uriBuilder -> uriBuilder //Билдит URL
                        .path(CANDLES_PATH)
                        .queryParam("from", from.toString())
                        .queryParam("interval", 24) //Запрос дневных свечей
                        .build(ticker)) //Передача аргумента в плейсхолдер
                .retrieve() //Переключение в режим обработки ответа
                .body(MoexCandlesResponse.class); //Магия: выполнение запроса и десериализация ответа
    }
}
