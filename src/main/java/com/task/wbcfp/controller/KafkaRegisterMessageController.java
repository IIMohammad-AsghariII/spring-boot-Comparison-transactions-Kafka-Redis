package com.task.wbcfp.controller;

import com.task.wbcfp.dto.ComparisonMessageDto;
import com.task.wbcfp.kafka.KafkaMessageProducer;
import com.task.wbcfp.model.TransactionRedis;
import com.task.wbcfp.payload.ComparisonMessage;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is responsible for sending the transaction comparison result to kafka.
 * * Of course, using this control is optional
 */
@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaRegisterMessageController {

    private KafkaMessageProducer kafkaMessageProducer;
    private ModelMapper modelMapper;

    public KafkaRegisterMessageController(KafkaMessageProducer kafkaMessageProducer, ModelMapper modelMapper) {
        this.kafkaMessageProducer = kafkaMessageProducer;
        this.modelMapper = modelMapper;
    }
// transaction comparison response message
    @PostMapping("/message")
    public ResponseEntity<String> publish(@RequestBody ComparisonMessageDto comparisonMessageDto){
        ComparisonMessage comparisonMessage = modelMapper.map(comparisonMessageDto,ComparisonMessage.class);
        kafkaMessageProducer.sendMessage(comparisonMessage);
        return ResponseEntity.ok("message sent to kafka transactions_result topic");
    }
}
