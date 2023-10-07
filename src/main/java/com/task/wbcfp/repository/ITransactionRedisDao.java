package com.task.wbcfp.repository;

import com.task.wbcfp.model.TransactionRedis;
import org.apache.kafka.common.protocol.types.Field;
import org.json.JSONObject;
public interface ITransactionRedisDao  {

    TransactionRedis saveTransaction(TransactionRedis transaction);
    String getTransactionById(Long id);
}
