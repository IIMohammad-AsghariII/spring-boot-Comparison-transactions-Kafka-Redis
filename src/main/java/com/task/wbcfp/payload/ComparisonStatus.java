package com.task.wbcfp.payload;

/**
 * Comparison message enum.
 */
public enum ComparisonStatus {

    /**
     * The transaction is Equal.
     */
    Given_Value_Equals_Valid_Amount,
    /**
     * The transaction Not Found.
     */
    Transaction_Not_Found,
    /**
     * The transaction is Invalid , Greater than The valid Amount.
     */
    Greater_Than_Valid_Amount,
    /**
     * The transaction is Invalid , Less than The valid Amount.
     */
    Less_Than_Valid_Amount,


}
