package com.task.wbcfp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.task.wbcfp.dto.ComparisonMessageDto;
import com.task.wbcfp.kafka.KafkaMessageProducer;
import com.task.wbcfp.payload.ComparisonStatus;
import com.task.wbcfp.service.SendComparisonResultImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SendComparisonResultController.class)
public class SendComparisonResultControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private SendComparisonResultImpl sendComparisonResult;

    @Test
    public void sendMessageTest() throws Exception {

        ComparisonMessageDto comparisonMessageDto = new ComparisonMessageDto();
        comparisonMessageDto.setId(123L);
        comparisonMessageDto.setComparisonStatus(ComparisonStatus.Transaction_Not_Found);
        comparisonMessageDto.setMessage("Transaction_Not_Found");
        mvc.perform(post("/api/v1/sendResultMessage").content(asJsonString(comparisonMessageDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Result Comparison message sent to API"))
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
