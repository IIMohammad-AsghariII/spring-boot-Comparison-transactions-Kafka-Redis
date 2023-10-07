package com.task.wbcfp.controller;

import com.task.wbcfp.config.KafkaManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is responsible for pausing and resuming the consumer receiving transactions.
 * This controller is used when the transaction receiving source needs to be changed from Kafka to API.
 * Of course, using this control is optional
 */
@RestController
@RequestMapping("/api/v1/kafka")
public class ConsumerChangeStatusController {
    private KafkaManager kafkaManager;

    public ConsumerChangeStatusController( KafkaManager kafkaManager) {
        this.kafkaManager = kafkaManager;
    }

    // resume  kafka consumer controller
    @GetMapping("/resume")
    public String resumeConsumer() {
        kafkaManager.resume();
        return "Consumer resume successfully";
    }

    // pause  kafka consumer controller
    @GetMapping("/pause")
    public String pauseConsumer() {
        kafkaManager.pause();
        return "Consumer pause successfully";
    }
}
