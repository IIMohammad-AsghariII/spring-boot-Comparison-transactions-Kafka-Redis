package com.task.wbcfp.repository;

import com.task.wbcfp.config.RedisConfig;
import com.task.wbcfp.exception.ResourceNotFoundException;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import com.task.wbcfp.model.TransactionRedis;

/**
 * Transaction Redis Repository
 */
@Repository
public class TransactionRedisDaoImpl implements ITransactionRedisDao {

    @Resource(name="stringTransactionReceiveKafkaTemplate")
    private StringRedisTemplate redisTemplate;


    @Override
    public TransactionRedis saveTransaction(TransactionRedis transaction) {
//         ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
//                 RedisConfig.class);
//         StringRedisTemplate redisTemplate = (StringRedisTemplate) ctx.getBean("stringTransactionReceiveKafkaTemplate");

        ValueOperations values = redisTemplate.opsForValue();
                values.set(transaction.getId().toString(), transaction.toString());
        return transaction;
    }

    @Override
    public String getTransactionById(Long id) {
//        ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(
//                RedisConfig.class);
//        StringRedisTemplate redisTemplate = (StringRedisTemplate) ctx.getBean("stringTransactionReceiveKafkaTemplate");
        try {
            ValueOperations values = redisTemplate.opsForValue();
            String transaction = values.get(id.toString()).toString();
            return transaction;
        } catch (NullPointerException e) {
            throw new ResourceNotFoundException("Transaction not exist with id: " + id);
        }
    }
}
