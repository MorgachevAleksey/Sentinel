package com.fintech.trade_bot.sentinel.client;

import com.fintech.trade_bot.sentinel.config.MarketDataProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.time.Duration;

@Configuration
public class RestClientConfig {
    @Bean
    public RestClient moexRstClient(MarketDataProperties properties){
        HttpClient httpClient = HttpClient.newBuilder()
                //Кастомное время ожидания рукопожатия
                .connectTimeout(Duration.ofSeconds(properties.getTimeoutSeconds())) //Ради этой строчки - задания кастомного времени ожидания ответа, создание не через вшитую фабрику Spring (ну и соответсвенно не вшитый клиент)
                .build();

        JdkClientHttpRequestFactory factory = new JdkClientHttpRequestFactory(httpClient);
        //Кастомной время ожидания данных
        factory.setReadTimeout(Duration.ofSeconds(properties.getTimeoutSeconds())); //И ради этой

        return RestClient.builder()
                .baseUrl(properties.getBaseUrl())
                .requestFactory(factory)
                .defaultHeader(HttpHeaders.USER_AGENT, "sentinel-trading-bot/0.1")
                .build();
    }
}
