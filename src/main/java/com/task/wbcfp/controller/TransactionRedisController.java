package com.task.wbcfp.controller;

import com.task.wbcfp.dto.TransactionRedisDto;
import com.task.wbcfp.model.TransactionRedis;
import com.task.wbcfp.service.TransactionRedisService;
import com.task.wbcfp.service.TransactionRedisServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This controller is  responsible for inserting and retrieving information from the Redis database.
 * I developed this controller to insert data and test data retrieval in Redis.
 * * Of course, using this control is optional
 */
@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionRedisController {

    private ModelMapper modelMapper;
    private TransactionRedisService transactionService;

    public TransactionRedisController(ModelMapper modelMapper, TransactionRedisService transactionService) {
        this.modelMapper = modelMapper;
        this.transactionService = transactionService;
    }
    // get transaction by id in redis
    @GetMapping("/{id}")
    public String getTransactionById(@PathVariable(name = "id") Long Id) {
        String transaction = transactionService.getTransactionById(Id);
        return transaction;
    }
    // set transaction in redis
    @PostMapping
    public ResponseEntity<TransactionRedisDto> saveTransaction(@RequestBody TransactionRedisDto transactionRedisDto) {
        TransactionRedis transactionRedis = modelMapper.map(transactionRedisDto,TransactionRedis.class);
        TransactionRedis newTransaction = transactionService.saveTransaction(transactionRedis);
        TransactionRedisDto transactionRedisDtoResponse = modelMapper.map(newTransaction,TransactionRedisDto.class);
        return new ResponseEntity<TransactionRedisDto>(transactionRedisDtoResponse, HttpStatus.CREATED);
    }

}
