package com.task.wbcfp.controller;

import com.task.wbcfp.dto.ComparisonMessageDto;
import com.task.wbcfp.payload.ComparisonMessage;
import com.task.wbcfp.service.SendComparisonResult;
import com.task.wbcfp.service.SendComparisonResultImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller is responsible for sending the transaction comparison response to external API
 * * This controller is to answer the third part of the requirements.
 */
@RestController
@RequestMapping("/api/v1/sendResultMessage")
public class SendComparisonResultController {
    private SendComparisonResult sendComparisonResult;

    private ModelMapper modelMapper;

    public SendComparisonResultController(SendComparisonResult sendComparisonResult, ModelMapper modelMapper) {
        this.sendComparisonResult = sendComparisonResult;
        this.modelMapper = modelMapper;
    }

    // sending the result of Transaction Comparison to API
    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody ComparisonMessageDto comparisonMessageDto){
        ComparisonMessage comparisonMessage = modelMapper.map(comparisonMessageDto,ComparisonMessage.class);
        sendComparisonResult.SendResultMessage(comparisonMessage);
        return ResponseEntity.ok("Result Comparison message sent to API");
    }
}
