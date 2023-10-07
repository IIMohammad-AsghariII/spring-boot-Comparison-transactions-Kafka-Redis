package com.task.wbcfp.service;

import com.task.wbcfp.model.TransactionAPI;
import com.task.wbcfp.repository.ITransactionAPIDao;
import com.task.wbcfp.repository.TransactionAPIDaoImpl;
import org.springframework.stereotype.Service;

/**
 * This service is responsible for receiving transactions through the API.
 */
@Service
public class TransactionAPIServiceImpl implements TransactionAPIService {

    private ITransactionAPIDao transactionAPIDao;

    public TransactionAPIServiceImpl(ITransactionAPIDao transactionAPIDao) {
        this.transactionAPIDao = transactionAPIDao;
    }

    @Override
    public TransactionAPI saveTransaction(TransactionAPI transactionAPI) {
        return transactionAPIDao.saveTransaction(transactionAPI);
    }

    @Override
    public String getTransactionById(Long id) {
        String transaction = transactionAPIDao.getTransactionById(id);
        return transaction;
    }
}
