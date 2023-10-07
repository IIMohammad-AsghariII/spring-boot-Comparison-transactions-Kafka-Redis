package com.task.wbcfp.controller;

import com.task.wbcfp.dto.TransactionKafkaDto;
import com.task.wbcfp.kafka.KafkaTransactionsProducer;
import com.task.wbcfp.payload.TransactionKafka;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This controller is responsible for registering transactions in the Kafka topic.
 * * Of course, using this control is optional
 */
@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaRegisterTransactionController {
    private KafkaTransactionsProducer kafkaTransactionsProducer;
    private  ModelMapper modelMapper;

    public KafkaRegisterTransactionController(KafkaTransactionsProducer kafkaTransactionsProducer ,ModelMapper modelMapper) {
        this.kafkaTransactionsProducer = kafkaTransactionsProducer;
        this.modelMapper =modelMapper;
    }

   // Registering the list of transactions in the Kafka Topic
    @PostMapping("/register")
    public ResponseEntity<String> publish(@RequestBody List<TransactionKafkaDto> transactionsKafka) {

        kafkaTransactionsProducer.sendTransaction(transactionsKafka.stream().map((dto) ->  modelMapper.map(dto, TransactionKafka.class)).collect(Collectors.toList()));
        return ResponseEntity.ok("message sent to kafka transactions_receive topic");
    }
}
