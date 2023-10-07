package com.task.wbcfp.service;

import com.task.wbcfp.model.TransactionRedis;

import com.task.wbcfp.repository.ITransactionRedisDao;
import org.springframework.stereotype.Service;

/**
 * This service is  responsible for recording and retrieving information from the Redis database.
 */
@Service
public class TransactionRedisServiceImpl implements TransactionRedisService {

    private ITransactionRedisDao transactionRedisDao;

    public TransactionRedisServiceImpl(ITransactionRedisDao transactionRedisDao) {
        this.transactionRedisDao = transactionRedisDao;
    }

    @Override
    public TransactionRedis saveTransaction(TransactionRedis transactionRedis) {
        return transactionRedisDao.saveTransaction(transactionRedis);
    }

    @Override
    public String getTransactionById(Long id) {
        String transaction = transactionRedisDao.getTransactionById(id);
        return transaction;
    }
}
