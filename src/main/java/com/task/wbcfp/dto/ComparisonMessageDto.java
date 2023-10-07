package com.task.wbcfp.dto;

import com.task.wbcfp.payload.ComparisonStatus;

/**
 * Data Transfer Objects comparison Message payload
 */
public class ComparisonMessageDto {
    private Long id;
    private ComparisonStatus comparisonStatus;
    private String message;

    public ComparisonMessageDto() {
    }

    public ComparisonMessageDto(Long id, ComparisonStatus comparisonStatus, String message) {
        this.id = id;
        this.comparisonStatus = comparisonStatus;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ComparisonStatus getComparisonStatus() {
        return comparisonStatus;
    }

    public void setComparisonStatus(ComparisonStatus comparisonStatus) {
        this.comparisonStatus = comparisonStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
