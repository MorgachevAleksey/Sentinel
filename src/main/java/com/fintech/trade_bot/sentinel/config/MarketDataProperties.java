package com.fintech.trade_bot.sentinel.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sentinel.market-data")
public class MarketDataProperties {
    private String baseUrl;
    private String timeoutSeconds;
    private Retry retry = new Retry();

    public static class Retry{
        private int maxAttempts;
        private long backoffMs;

        public void setMaxAttempts(int maxAttempts){
            this.maxAttempts = maxAttempts;
        }
        public void setBackoffMs(int backoffMs){
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
