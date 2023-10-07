package com.task.wbcfp.payload;

/**
 * Comparison message payload.
 */
public class ComparisonMessage {
    private Long id;
    private ComparisonStatus comparisonStatus;
    private String message;

    public ComparisonMessage() {
    }

    public ComparisonMessage(Long id, ComparisonStatus comparisonStatus, String message) {
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

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", comparisonStatus=" + comparisonStatus +
                ", message='" + message + '\'' +
                '}';
    }
}
