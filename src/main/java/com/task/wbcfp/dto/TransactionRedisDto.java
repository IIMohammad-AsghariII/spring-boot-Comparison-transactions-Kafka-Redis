package com.task.wbcfp.dto;

import com.task.wbcfp.model.TransactionRedisMetadata;

import java.math.BigDecimal;

/**
 * Data Transfer Objects Transaction Redis Model
 */

public class TransactionRedisDto {
    private Long id;

    private BigDecimal amount;
    private TransactionRedisMetadata metadata;

    public TransactionRedisDto() {
    }

    public TransactionRedisDto(Long id, BigDecimal amount, TransactionRedisMetadata metadata) {
        this.id = id;
        this.amount = amount;
        this.metadata = metadata;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionRedisMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(TransactionRedisMetadata metadata) {
        this.metadata = metadata;
    }


}
