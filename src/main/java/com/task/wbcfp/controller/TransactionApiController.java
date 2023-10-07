package com.task.wbcfp.controller;

import com.task.wbcfp.dto.TransactionAPIDto;
import com.task.wbcfp.exception.ResourceNotFoundException;
import com.task.wbcfp.model.TransactionAPI;

import com.task.wbcfp.service.*;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * This control is responsible for receiving transactions through the API.
 ** This controller is to answer the first part of the requirements.
 */
@RestController
@RequestMapping("/api/v1/receiveTransaction")
public class TransactionApiController {
    private ModelMapper modelMapper;
    private TransactionAPIService transactionAPIService;

    private TransactionRedisService transactionRedisService;
    private ComparisonService comparisonService;

    public TransactionApiController(ModelMapper modelMapper, TransactionAPIService transactionAPIService, TransactionRedisService transactionRedisService, ComparisonService comparisonService) {
        this.modelMapper = modelMapper;
        this.transactionAPIService = transactionAPIService;
        this.transactionRedisService = transactionRedisService;
        this.comparisonService = comparisonService;
    }

    // for save and process receive transaction from API
    @PostMapping("/process")
    public ResponseEntity<TransactionAPIDto> processTransaction(@RequestBody TransactionAPIDto transactionAPIDto) {
        TransactionAPI transactionAPI = modelMapper.map(transactionAPIDto, TransactionAPI.class);
        TransactionAPI newTransaction = transactionAPIService.saveTransaction(transactionAPI);
        TransactionAPIDto transactionAPIDtoResponse = modelMapper.map(newTransaction, TransactionAPIDto.class);
        //Comparison received amount with redis db amount
        try {
            String redisTransaction = transactionRedisService.getTransactionById(newTransaction.getpID());
            JSONObject jObject = new JSONObject(redisTransaction);
            BigDecimal amountRedis = (BigDecimal) jObject.get("amount");
            comparisonService.Comparison(newTransaction.getpID(), newTransaction.getpAMOUNT(), amountRedis);

        } catch (ResourceNotFoundException ex) {
            comparisonService.Comparison(newTransaction.getpID(), newTransaction.getpAMOUNT(), null);

        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
        return new ResponseEntity<TransactionAPIDto>(transactionAPIDtoResponse, HttpStatus.CREATED);
    }



    // for save receive transaction from API . this method just for log transaction  in Redis.
    @PostMapping("/store")
    public ResponseEntity<TransactionAPIDto> saveTransaction(@RequestBody TransactionAPIDto transactionAPIDto) {
        TransactionAPI transactionAPI = modelMapper.map(transactionAPIDto, TransactionAPI.class);
        TransactionAPI newTransaction = transactionAPIService.saveTransaction(transactionAPI);
        TransactionAPIDto transactionAPIDtoResponse = modelMapper.map(newTransaction, TransactionAPIDto.class);
        return new ResponseEntity<TransactionAPIDto>(transactionAPIDtoResponse, HttpStatus.CREATED);
    }




}
