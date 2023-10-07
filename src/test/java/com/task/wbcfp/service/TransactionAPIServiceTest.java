package com.task.wbcfp.service;

import com.task.wbcfp.model.TransactionAPI;
import com.task.wbcfp.repository.TransactionAPIDaoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionAPIServiceTest {

    @MockBean
    private TransactionAPIDaoImpl transactionAPIDao;

    @Autowired
    private TransactionAPIService transactionAPIService;

    @Test
    void saveTransactionTest() {
        TransactionAPI transactionAPI = new TransactionAPI(123L, BigDecimal.valueOf(94.7),"20160101120000");
        when(transactionAPIDao.saveTransaction(any(TransactionAPI.class))).thenReturn(transactionAPI);
        TransactionAPI savedTransactionAPI = transactionAPIService.saveTransaction(transactionAPI);
        assertThat(savedTransactionAPI.getpAMOUNT()).isNotNull();
    }

    @Test
    void getTransactionByIdTest(){
        long Id = 126L;
        String result = "{\"pID\":126,\"pAMOUNT\":100.7,\"pDATA\":\"20160101120000\"}";
        when(transactionAPIDao.getTransactionById(Id)).thenReturn(result);
        String getTransactionRedis = transactionAPIService.getTransactionById(Id);
        assertThat(getTransactionRedis).isEqualTo(result);


    }
}
