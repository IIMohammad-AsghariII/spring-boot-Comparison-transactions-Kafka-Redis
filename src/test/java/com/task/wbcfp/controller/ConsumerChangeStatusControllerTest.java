package com.task.wbcfp.controller;

import com.task.wbcfp.config.KafkaManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ConsumerChangeStatusController.class)
public class ConsumerChangeStatusControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private KafkaManager kafkaManager;

    @Test
    public void resumeConsumerTest() throws Exception {
        mvc.perform(get("/api/v1/kafka/resume")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Consumer resume successfully"))
                .andDo(print());


    }

    @Test
    public void pauseConsumerTest() throws Exception {
        mvc.perform(get("/api/v1/kafka/pause")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Consumer pause successfully"))
                .andDo(print());


    }

}

