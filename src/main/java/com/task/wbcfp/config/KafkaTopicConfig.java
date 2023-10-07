package com.task.wbcfp.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * This class performs the task of setting pause and resume of the Kafka consumer.
 */
@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic-receive.name}")
    private String topicReceive;

    @Value("${spring.kafka.topic-result.name}")
    private String topicMessage;

    //The topic of receiving transactions
    @Bean
    public NewTopic KafkaReceiveTopic() {
        return TopicBuilder.name(topicReceive)
                .build();
    }

    //The topic of sending transaction comparisons result
    @Bean
    public NewTopic KafkaMessageTopic() {
        return TopicBuilder.name(topicMessage)
                .build();
    }
}