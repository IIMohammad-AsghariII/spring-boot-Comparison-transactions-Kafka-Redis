package com.task.wbcfp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.wbcfp.dto.TransactionRedisDto;
import com.task.wbcfp.model.TransactionRedis;
import com.task.wbcfp.model.TransactionRedisMetadata;
import com.task.wbcfp.service.TransactionRedisServiceImpl;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;


import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

@WebMvcTest(TransactionRedisController.class)
public class TransactionRedisControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ModelMapper modelMapper;
    @MockBean
    private TransactionRedisServiceImpl transactionService;

    @Test
    public void getTransactionByIdTest() throws Exception {
        long Id = 126L;
        String result = "{\"amount\":15.5,\"metadata\":{\"originatorId\":30,\"destinationId\":40}}";
        when(transactionService.getTransactionById(Id)).thenReturn(result);
        mvc.perform(get("/api/v1/transaction/{id}", Id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(result))
                .andDo(print());
    }

    @Test
    public void saveTransactionTest() throws Exception {
        long Id = 126L;
        TransactionRedisDto transactionRedisDto = new TransactionRedisDto();
        transactionRedisDto.setId(Id);
        transactionRedisDto.setAmount(BigDecimal.valueOf(15.5));
        transactionRedisDto.setMetadata(new TransactionRedisMetadata(30, 40));
        TransactionRedis transactionRedis = modelMapper.map(transactionRedisDto, TransactionRedis.class);
        when(transactionService.saveTransaction(any(TransactionRedis.class))).thenReturn(transactionRedis);
        mvc.perform(post("/api/v1/transaction").content(asJsonString(transactionRedisDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(Id))
                .andExpect(jsonPath("$.amount").value(transactionRedisDto.getAmount()))
                .andExpect(jsonPath("$.metadata.originatorId").value(transactionRedisDto.getMetadata().getOriginatorId()))
                .andExpect(jsonPath("$.metadata.destinationId").value(transactionRedisDto.getMetadata().getDestinationId()))
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
