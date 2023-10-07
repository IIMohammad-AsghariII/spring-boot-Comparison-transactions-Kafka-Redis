package com.task.wbcfp.kafka;

import com.task.wbcfp.service.ComparisonServiceImpl;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.wbcfp.exception.ResourceNotFoundException;
import com.task.wbcfp.payload.ComparisonMessage;
import com.task.wbcfp.payload.ComparisonStatus;
import com.task.wbcfp.payload.TransactionKafka;
import com.task.wbcfp.service.ComparisonService;
import com.task.wbcfp.service.TransactionRedisServiceImpl;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import java.math.BigDecimal;
import java.util.List;

/**
 * This service is responsible for receive the transaction from kafka topic and  comparing it with Redis database transactions
 */
@Service
public class KafkaTransactionsConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTransactionsConsumer.class);

    private TransactionRedisServiceImpl transactionService;
    private ComparisonServiceImpl comparisonService;

    public KafkaTransactionsConsumer(TransactionRedisServiceImpl transactionService, ComparisonServiceImpl comparisonService) {
        this.transactionService = transactionService;
        this.comparisonService = comparisonService;
    }

    @KafkaListener(id = "transaction_Listener", topics = "${spring.kafka.topic-receive.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(List<TransactionKafka> transactionsKafka) {
        ObjectMapper mapper = new ObjectMapper();

        LOGGER.info(String.format(" Transaction received -> %s", transactionsKafka.toString()));
        //Comparison received amount with redis db amount
        if (!transactionsKafka.isEmpty()) {
            for (Object obj : transactionsKafka) {
                TransactionKafka kafkaTransaction = null;
                try {
                    kafkaTransaction = mapper.convertValue(obj, TransactionKafka.class);
                    //compare the amount field of the received transaction against the transaction in Redis

                    String redisTransaction = transactionService.getTransactionById(kafkaTransaction.getpID());
                    JSONObject jObject = new JSONObject(redisTransaction);
                    BigDecimal amountRedis = (BigDecimal) jObject.get("amount");
                    comparisonService.Comparison(kafkaTransaction.getpID(), kafkaTransaction.getpAMOUNT(), amountRedis);

                } catch (ResourceNotFoundException ex) {
                    comparisonService.Comparison(kafkaTransaction.getpID(), kafkaTransaction.getpAMOUNT(), null);

                }
                catch (Throwable throwable) {
                    System.out.println(throwable.getMessage());
                }
            }
        }
    }
}
