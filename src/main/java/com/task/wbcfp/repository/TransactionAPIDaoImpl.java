package com.task.wbcfp.repository;

import com.task.wbcfp.model.TransactionAPI;
import com.task.wbcfp.config.RedisConfig;
import com.task.wbcfp.exception.ResourceNotFoundException;
import jakarta.annotation.Resource;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * Transaction API Repository
 */
@Repository
public class TransactionAPIDaoImpl implements ITransactionAPIDao {

    @Resource(name = "stringTransactionReceiveAPITemplate")
    private StringRedisTemplate redisTemplate;

    @Override
    public TransactionAPI saveTransaction(TransactionAPI transactionAPI) {
//        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
//                RedisConfig.class);
//        StringRedisTemplate redisTemplate = (StringRedisTemplate) ctx.getBean("stringTransactionReceiveAPITemplate");

        ValueOperations values = redisTemplate.opsForValue();
        values.set(transactionAPI.getpID().toString(), transactionAPI.toString());
        return transactionAPI;
    }

    @Override
    public String getTransactionById(Long id) {
//        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
//                RedisConfig.class);
//        StringRedisTemplate redisTemplate = (StringRedisTemplate) ctx.getBean("stringTransactionReceiveAPITemplate");
        try {
            ValueOperations values = redisTemplate.opsForValue();
            String transaction = values.get(id.toString()).toString();
            return transaction;
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException("Transaction not exist with id: " + id);
        }
    }
}
