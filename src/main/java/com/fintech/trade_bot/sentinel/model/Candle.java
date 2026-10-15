package com.fintech.trade_bot.sentinel.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "candles")  //Нужно указать явно потому что имя таблицы расходится с именем класса
public class Candle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 20)
    private String ticker;
    @Column(nullable = false, length = 16)
    private String timeframe;
    @Column(name = "open_time", nullable = false)
    private Instant openTime;
    @Column(name = "open_price", nullable = false, precision = 19, scale = 6)
    private BigDecimal openPrice;
    @Column(name = "low_price", nullable = false, precision = 19, scale = 6)
    private BigDecimal lowPrice;
    @Column(name = "high_price", nullable = false, precision = 19, scale = 6)
    private BigDecimal highPrice;
    @Column(name = "close_price", nullable = false, precision = 19, scale = 6)
    private BigDecimal closePrice;
    @Column(nullable = false, precision = 24, scale = 6)
    private BigDecimal volume;

    public Candle(){

    }
    public Candle(
            long id,
            String ticker,
            String timeFrame,
            Instant openTime,
            BigDecimal openPrice,
            BigDecimal lowPrice,
            BigDecimal highPrice,
            BigDecimal closePrice,
            BigDecimal volume
            ){
        this.ticker = ticker;
        this.timeframe = timeFrame;
        this.openTime = openTime;
        this.openPrice = openPrice;
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.closePrice = closePrice;
        this.volume = volume;
    }

    public long getId() {
        return id;
    }
    public String getTicker() {
        return ticker;
    }
    public String getTimeFrame() {
        return timeframe;
    }
    public Instant getOpenTime() {
        return openTime;
    }
    public BigDecimal getOpenPrice() {
        return openPrice;
    }
    public BigDecimal getLowPrice() {
        return lowPrice;
    }
    public BigDecimal getHighPrice() {
        return highPrice;
    }
    public BigDecimal getClosePrice() {
        return closePrice;
    }
    public BigDecimal getVolume() {
        return volume;
    }
}
