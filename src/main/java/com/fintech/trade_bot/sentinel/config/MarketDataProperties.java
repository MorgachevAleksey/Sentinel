package com.fintech.trade_bot.sentinel.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "sentinel.market-data")
@Validated
public class MarketDataProperties {
    @NotBlank
    private String baseUrl;
    private String timeoutSeconds;
    @Valid
    private Retry retry = new Retry();

    public static class Retry{
        private int maxAttempts;
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
    public void setTimeoutSeconds(String timeoutSeconds){
        this.timeoutSeconds = timeoutSeconds;
    }
    public void setRetry(Retry retry){
        this.retry = retry;
    }

    public String getBaseUrl(){
        return this.baseUrl;
    }
    public String getTimeoutSeconds(){
        return this.timeoutSeconds;
    }
    public Retry getRetry(){
        return this.retry;
    }
}
