package com.task.wbcfp.service;

import com.task.wbcfp.payload.ComparisonMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Collections;

/**
 *  This service is responsible for sending the transaction comparison response to external API
 *  you can config external url in application.yml
 */
@Service
public class SendComparisonResultImpl implements SendComparisonResult {

    @Value("${API.url}")
    private String url;

    @Override
    public String SendResultMessage(ComparisonMessage message) {

        String apiUrl = url;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        ResponseEntity<ComparisonMessage> result = restTemplate.postForEntity(apiUrl, message, ComparisonMessage.class);

        // check response
        if (result.getStatusCode() == HttpStatus.CREATED) {
            return "The message was successfully sent";

        } else {

            return "Request Failed";

        }

    }
}
