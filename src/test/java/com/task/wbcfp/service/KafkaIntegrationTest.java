package com.task.wbcfp.service;


import com.task.wbcfp.kafka.KafkaTransactionsConsumer;
import com.task.wbcfp.kafka.KafkaTransactionsProducer;
import com.task.wbcfp.payload.TransactionKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
@SpringBootTest
public class KafkaIntegrationTest {

    @Autowired
    private KafkaTransactionsConsumer consumer;

    @Autowired
    private KafkaTransactionsProducer producer;

    @Value("${spring.kafka.topic-receive.name}")
    private String topicTransaction;

    @Test
    public void KafkaTransactionTest() throws Exception {

        List<TransactionKafka> transactionList = new ArrayList<>();
        TransactionKafka transaction1 = new TransactionKafka();
        //transaction1
        transaction1.setpID(123L);
        transaction1.setpAMOUNT(BigDecimal.valueOf(94.7));
        transaction1.setpDATA("20160101120000");
        transactionList.add(transaction1);
        //transaction2
        TransactionKafka transaction2 = new TransactionKafka();
        transaction2.setpID(124L);
        transaction2.setpAMOUNT(BigDecimal.valueOf(150.75));
        transaction2.setpDATA("20160101120001");
        transactionList.add(transaction2);

        producer.sendTransaction(transactionList);

        consumer.consume(transactionList);

    }
}
