package com.task.wbcfp.payload;

import java.math.BigDecimal;

/**
 * Transaction Kafka payload.
 */
public class TransactionKafka {
    private Long pID;
    private BigDecimal pAMOUNT;
    private String pDATA;

    public TransactionKafka() {
    }

    public TransactionKafka(Long pID, BigDecimal  pAMOUNT, String pDATA) {
        this.pID = pID;
        this.pAMOUNT = pAMOUNT;
        this.pDATA = pDATA;
    }

    public Long getpID() {
        return pID;
    }

    public void setpID(Long pID) {
        this.pID = pID;
    }

    public BigDecimal  getpAMOUNT() {
        return pAMOUNT;
    }

    public void setpAMOUNT(BigDecimal pAMOUNT) {
        this.pAMOUNT = pAMOUNT;
    }

    public String getpDATA() {
        return pDATA;
    }

    public void setpDATA(String pDATA) {
        this.pDATA = pDATA;
    }

    @Override
    public String toString() {
        return "{" +
                "pID=" + pID +
                ", pAMOUNT=" + pAMOUNT +
                ", pDATA='" + pDATA + '\'' +
                '}';
    }
}
