package com.task.wbcfp.kafka;

import com.task.wbcfp.payload.ComparisonMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * This service is responsible for sending the transaction comparison result in kafka.
 */
@Service
public class KafkaMessageProducer {

    @Value("${spring.kafka.topic-result.name}")
    private String topicMessage;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTransactionsProducer.class);

    private KafkaTemplate<String, ComparisonMessage> kafkaTemplate;

    public KafkaMessageProducer(KafkaTemplate<String, ComparisonMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(ComparisonMessage comparisonMessage) {

        LOGGER.info(String.format("Message sent -> %s", comparisonMessage.toString()));

        Message<ComparisonMessage> message = MessageBuilder
                .withPayload(comparisonMessage)
                .setHeader(KafkaHeaders.TOPIC, topicMessage)
                .build();

        kafkaTemplate.send(message);
    }
}
