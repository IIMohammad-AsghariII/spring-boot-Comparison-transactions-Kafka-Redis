package com.task.wbcfp.service;

import com.task.wbcfp.model.TransactionAPI;

public interface TransactionAPIService {

    public TransactionAPI saveTransaction(TransactionAPI transactionAPI);

    public String getTransactionById(Long id);
}
