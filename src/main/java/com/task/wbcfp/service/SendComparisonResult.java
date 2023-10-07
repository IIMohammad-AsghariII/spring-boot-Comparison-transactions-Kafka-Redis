package com.task.wbcfp.service;

import com.task.wbcfp.payload.ComparisonMessage;
import org.springframework.beans.factory.annotation.Value;

public interface SendComparisonResult {

    String SendResultMessage(ComparisonMessage message);


}
