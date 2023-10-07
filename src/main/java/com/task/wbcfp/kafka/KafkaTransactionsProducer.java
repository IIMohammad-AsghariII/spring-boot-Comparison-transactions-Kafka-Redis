package com.task.wbcfp.kafka;

import com.task.wbcfp.payload.TransactionKafka;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This service is responsible for registering transactions in the Kafka topic.
 */
@Service
public class KafkaTransactionsProducer {

    @Value("${spring.kafka.topic-receive.name}")
    private String topicTransaction;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTransactionsProducer.class);

    private KafkaTemplate<String, TransactionKafka> kafkaTemplate;

    public KafkaTransactionsProducer(KafkaTemplate<String, TransactionKafka> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendTransaction(List<TransactionKafka> data) {

        LOGGER.info(String.format("Transaction sent -> %s", data.toString()));

        Message<List<TransactionKafka>> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, topicTransaction)
                .build();

        kafkaTemplate.send(message);
    }
}
