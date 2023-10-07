package com.task.wbcfp.service;

import java.math.BigDecimal;

public interface ComparisonService {
    public void Comparison(Long id, BigDecimal receiveAmount, BigDecimal redisDbAmount);

}
