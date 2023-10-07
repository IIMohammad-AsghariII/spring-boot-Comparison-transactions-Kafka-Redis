package com.task.wbcfp.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * This class is responsible for Redis Connection and RedisTemplate
 */
@Configuration
@PropertySource("classpath:application.properties")
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String host;
    @Value("${spring.data.redis.port}")
    private Integer port;
    @Value("${spring.data.redis.database}")
    private Integer redisTransactionKafkaDb;
    @Value("${com.task.data.redis.database}")
    private Integer redisTransactionReceiveAPIDb;

    @Bean
    @Primary
    JedisConnectionFactory redisTransactionReceiveKafkaConnectionFactory() {
        JedisConnectionFactory jedisConFactory
                = new JedisConnectionFactory();
        jedisConFactory.setHostName(host);
        jedisConFactory.setPort(port);
        jedisConFactory.setDatabase(redisTransactionKafkaDb);
        return jedisConFactory;
    }

    //Creating StringRedisTemplate for Entity 'TransactionKafka'
    @Bean
    StringRedisTemplate stringTransactionReceiveKafkaTemplate() {

        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisTransactionReceiveKafkaConnectionFactory());
        return template;
    }

    @Bean
    JedisConnectionFactory redisTransactionReceiveAPIConnectionFactory() {
        JedisConnectionFactory jedisConFactory
                = new JedisConnectionFactory();
        jedisConFactory.setHostName(host);
        jedisConFactory.setPort(port);
        jedisConFactory.setDatabase(redisTransactionReceiveAPIDb);
        return jedisConFactory;
    }


    //Creating StringRedisTemplate for Entity 'TransactionReceiveAPI'
    @Bean
    StringRedisTemplate stringTransactionReceiveAPITemplate() {

        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisTransactionReceiveAPIConnectionFactory());
        return template;
    }


}