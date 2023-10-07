package com.task.wbcfp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.wbcfp.dto.TransactionKafkaDto;
import com.task.wbcfp.kafka.KafkaTransactionsProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(KafkaRegisterTransactionController.class)
public class KafkaRegisterTransactionControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private KafkaTransactionsProducer kafkaTransactionsProducer;

    @Test
    public void publishTest() throws Exception {
        List<TransactionKafkaDto> transactionList = new ArrayList<>();
        TransactionKafkaDto transaction1 = new TransactionKafkaDto();
        //transaction1
        transaction1.setpID(123L);
        transaction1.setpAMOUNT(BigDecimal.valueOf(94.7));
        transaction1.setpDATA("20160101120000");
        transactionList.add(transaction1);
        //transaction2
        TransactionKafkaDto transaction2 = new TransactionKafkaDto();
        transaction2.setpID(124L);
        transaction2.setpAMOUNT(BigDecimal.valueOf(150.75));
        transaction2.setpDATA("20160101120001");
        transactionList.add(transaction2);

        mvc.perform(post("/api/v1/kafka/register").content(asJsonString(transactionList))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("message sent to kafka transactions_receive topic"))
                .andDo(print());


    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
