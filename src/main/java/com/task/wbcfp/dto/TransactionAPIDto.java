package com.task.wbcfp.dto;

import java.math.BigDecimal;

/**
 * Data Transfer Objects Transaction API Model
 */
public class TransactionAPIDto {
    private Long pID;
    private BigDecimal pAMOUNT;
    private String pDATA;

    public TransactionAPIDto() {
    }

    public TransactionAPIDto(Long pID, BigDecimal pAMOUNT, String pDATA) {
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

    public BigDecimal getpAMOUNT() {
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
}
