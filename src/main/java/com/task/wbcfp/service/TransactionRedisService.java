package com.task.wbcfp.service;


import com.task.wbcfp.model.TransactionRedis;

public interface TransactionRedisService {

    public TransactionRedis saveTransaction(TransactionRedis transactionRedis);

    public String getTransactionById(Long id);


}
