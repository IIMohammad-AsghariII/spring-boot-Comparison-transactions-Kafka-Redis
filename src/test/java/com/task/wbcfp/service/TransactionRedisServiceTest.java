package com.task.wbcfp.service;

import com.task.wbcfp.model.TransactionRedis;
import com.task.wbcfp.model.TransactionRedisMetadata;
import com.task.wbcfp.repository.TransactionRedisDaoImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;

@SpringBootTest
public class TransactionRedisServiceTest {

    @MockBean
    private TransactionRedisDaoImpl transactionRedisDao;

    @Autowired
    private TransactionRedisService transactionRedisService;

    @Test
    void saveTransactionTest() {
        TransactionRedis transactionRedis = new TransactionRedis(123L, BigDecimal.valueOf(94.7), new TransactionRedisMetadata(1, 2));
        when(transactionRedisDao.saveTransaction(any(TransactionRedis.class))).thenReturn(transactionRedis);
        TransactionRedis savedTransactionRedis = transactionRedisService.saveTransaction(transactionRedis);
        assertThat(savedTransactionRedis.getAmount()).isNotNull();
    }

    @Test
    void getTransactionByIdTest(){
        long Id = 126L;
        String result = "{\"amount\":15.5,\"metadata\":{\"originatorId\":30,\"destinationId\":40}}";
        when(transactionRedisDao.getTransactionById(Id)).thenReturn(result);
        String getTransactionRedis = transactionRedisService.getTransactionById(Id);
        assertThat(getTransactionRedis).isEqualTo(result);


    }
}
