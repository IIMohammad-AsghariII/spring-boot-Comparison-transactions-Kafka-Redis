package com.task.wbcfp.service;

import org.springframework.stereotype.Service;

import com.task.wbcfp.kafka.KafkaMessageProducer;
import com.task.wbcfp.payload.ComparisonMessage;
import com.task.wbcfp.payload.ComparisonStatus;

import java.math.BigDecimal;

/**
 * This service is responsible for comparing the amount of transactions.
 */
@Service
public class ComparisonServiceImpl implements ComparisonService {

    private KafkaMessageProducer kafkaMessageProducer;

    public ComparisonServiceImpl(KafkaMessageProducer kafkaMessageProducer) {
        this.kafkaMessageProducer = kafkaMessageProducer;
    }

    @Override
    public void Comparison(Long id, BigDecimal receiveAmount, BigDecimal redisDbAmount) {
        ComparisonMessage message = new ComparisonMessage();
        message.setId(id);
        if (redisDbAmount == null) {
            message.setComparisonStatus(ComparisonStatus.Transaction_Not_Found);
            message.setMessage("Transaction not exist with id: " + id);
        } else if (receiveAmount.equals(redisDbAmount)) {

            message.setComparisonStatus(ComparisonStatus.Given_Value_Equals_Valid_Amount);
            message.setMessage("The transaction is Equal.");

        } else if (receiveAmount.compareTo(redisDbAmount) > 0) {
            message.setComparisonStatus(ComparisonStatus.Greater_Than_Valid_Amount);
            message.setMessage("Received amount " + receiveAmount + " which is " + (receiveAmount.subtract(redisDbAmount)) + " greater than the valid Amount. ");

        } else {
            message.setComparisonStatus(ComparisonStatus.Less_Than_Valid_Amount);
            message.setMessage("Received amount " + receiveAmount + " which is " + (redisDbAmount.subtract(receiveAmount)) + " less than the valid Amount. ");

        }
        kafkaMessageProducer.sendMessage(message);
    }
}
