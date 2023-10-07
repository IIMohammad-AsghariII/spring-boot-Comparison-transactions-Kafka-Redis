package com.task.wbcfp.repository;

import com.task.wbcfp.model.TransactionAPI;

public interface ITransactionAPIDao {
    TransactionAPI saveTransaction(TransactionAPI  transactionAPI);
    String getTransactionById(Long id);
}
