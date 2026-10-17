package com.fintech.trade_bot.sentinel.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "sentinel.market-data")
@Validated
public class MarketDataProperties {
    //Базовый URL путь для запросов приложения
    @NotBlank
    private String baseUrl;
    //Время ожидания ответа от сервера, в секундах
    private int timeoutSeconds;
    @Valid
    private Retry retry = new Retry();

    public static class Retry{
        //Максимальное кол-во повторынх запросов после возврата ошибки
        private int maxAttempts;
        //Время задержки между повторными запросами после ошибки, в милисекундах
        @Min(value = 0)
        private long backoffMs;

        public void setMaxAttempts(int maxAttempts){
            this.maxAttempts = maxAttempts;
        }
        public void setBackoffMs(long backoffMs){
            this.backoffMs = backoffMs;
        }

        public int getMaxAttempts(){
            return this.maxAttempts;
        }
        public long getBackoffMs(){
            return this.backoffMs;
        }
    }

    public void setBaseUrl(String baseUrl){
        this.baseUrl = baseUrl;
    }
    public void setTimeoutSeconds(int timeoutSeconds){
        this.timeoutSeconds = timeoutSeconds;
    }
    public void setRetry(Retry retry){
        this.retry = retry;
    }

    public String getBaseUrl(){
        return this.baseUrl;
    }
    public int getTimeoutSeconds(){
        return this.timeoutSeconds;
    }
    public Retry getRetry(){
        return this.retry;
    }
}
