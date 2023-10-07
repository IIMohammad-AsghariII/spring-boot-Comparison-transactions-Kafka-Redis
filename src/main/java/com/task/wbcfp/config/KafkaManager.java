package com.task.wbcfp.config;


import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.stereotype.Service;

/**
 * This class is responsible for kafka topics config
 */
@Service
public class KafkaManager {

    private KafkaListenerEndpointRegistry registry;

    public KafkaManager(KafkaListenerEndpointRegistry registry) {
        this.registry = registry;
    }

    // pause  kafka consumer method
    public void pause() {
        registry.getListenerContainer("transaction_Listener").pause();
    }

    // resume kafka consumer method
    public void resume() {
        registry.getListenerContainer("transaction_Listener").resume();
    }



}
