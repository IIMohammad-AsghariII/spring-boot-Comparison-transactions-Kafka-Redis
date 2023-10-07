package com.task.wbcfp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.wbcfp.dto.TransactionAPIDto;
import com.task.wbcfp.dto.TransactionRedisDto;
import com.task.wbcfp.model.TransactionAPI;
import com.task.wbcfp.model.TransactionRedis;
import com.task.wbcfp.model.TransactionRedisMetadata;
import com.task.wbcfp.service.ComparisonServiceImpl;
import com.task.wbcfp.service.TransactionAPIServiceImpl;
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

@WebMvcTest(TransactionApiController.class)
public class TransactionApiControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ModelMapper modelMapper;
    @MockBean
    private TransactionAPIServiceImpl transactionAPIService;
    @MockBean
    private TransactionRedisServiceImpl transactionRedisService;
    @MockBean
    private ComparisonServiceImpl comparisonService;

    @Test
    public void saveTransactionTest() throws Exception {
        long Id = 126L;
        TransactionAPIDto transactionAPIDto = new TransactionAPIDto();
        transactionAPIDto.setpID(Id);
        transactionAPIDto.setpAMOUNT(BigDecimal.valueOf(15.5));
        transactionAPIDto.setpDATA("20160101120003");
        TransactionAPI transactionAPI = modelMapper.map(transactionAPIDto, TransactionAPI.class);
        when(transactionAPIService.saveTransaction(any(TransactionAPI.class))).thenReturn(transactionAPI);
        mvc.perform(post("/api/v1/receiveTransaction/store").content(asJsonString(transactionAPIDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.pID").value(Id))
                .andExpect(jsonPath("$.pAMOUNT").value(transactionAPIDto.getpAMOUNT()))
                .andExpect(jsonPath("$.pDATA").value(transactionAPIDto.getpDATA()))
                .andDo(print());
    }

    @Test
    public void processTransactionTest() throws Exception {
        long Id = 126L;
        TransactionAPIDto transactionAPIDto = new TransactionAPIDto();
        transactionAPIDto.setpID(Id);
        transactionAPIDto.setpAMOUNT(BigDecimal.valueOf(15.5));
        transactionAPIDto.setpDATA("20160101120003");
        TransactionAPI transactionAPI = modelMapper.map(transactionAPIDto, TransactionAPI.class);
        when(transactionAPIService.saveTransaction(any(TransactionAPI.class))).thenReturn(transactionAPI);
        mvc.perform(post("/api/v1/receiveTransaction/process").content(asJsonString(transactionAPIDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.pID").value(Id))
                .andExpect(jsonPath("$.pAMOUNT").value(transactionAPIDto.getpAMOUNT()))
                .andExpect(jsonPath("$.pDATA").value(transactionAPIDto.getpDATA()))
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
