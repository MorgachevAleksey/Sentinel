package com.fintech.trade_bot.sentinel.repository;

import com.fintech.trade_bot.sentinel.model.Candle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandleRepository extends JpaRepository<Candle, Long> {

}
