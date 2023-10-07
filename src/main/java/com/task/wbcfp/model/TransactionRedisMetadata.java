package com.task.wbcfp.model;

/**
 * Transaction Redis Metadata model
 */
public class TransactionRedisMetadata {
    private int originatorId;
    private int destinationId;

    public TransactionRedisMetadata() {
    }

    public TransactionRedisMetadata(int originatorId, int destinationId) {
        this.originatorId = originatorId;
        this.destinationId = destinationId;
    }

    public int getOriginatorId() {
        return originatorId;
    }

    public void setOriginatorId(int originatorId) {
        this.originatorId = originatorId;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }

    @Override
    public String toString() {
        return "Transaction_Metadata{" +
                "originatorId=" + originatorId +
                ", destinationId=" + destinationId +
                '}';
    }
}
