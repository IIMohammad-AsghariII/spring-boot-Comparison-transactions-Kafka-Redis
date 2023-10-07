package com.task.wbcfp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Transaction Redis Model
 */
public class TransactionRedis implements Serializable {
    @Id
    @Indexed

    private Long id;

    private BigDecimal amount;
    private TransactionRedisMetadata metadata;

    public TransactionRedis() {
    }

    public TransactionRedis(Long id, BigDecimal amount, TransactionRedisMetadata metadata) {
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

    @Override
    public String toString() {
        return "{\"amount\":" + amount +
                ",\"metadata\":{\"originatorId\":" + metadata.getOriginatorId() +
                ",\"destinationId\":"  + metadata.getDestinationId()+
                "}"+
                '}';
    }

}
