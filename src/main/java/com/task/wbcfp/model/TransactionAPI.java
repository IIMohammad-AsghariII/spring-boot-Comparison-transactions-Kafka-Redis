package com.task.wbcfp.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Transaction API Model
 */
public class TransactionAPI implements Serializable {
    private Long pID;
    private BigDecimal pAMOUNT;
    private String pDATA;

    public TransactionAPI() {
    }

    public TransactionAPI(Long pID, BigDecimal  pAMOUNT, String pDATA) {
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

    public void setpAMOUNT(BigDecimal  pAMOUNT) {
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
