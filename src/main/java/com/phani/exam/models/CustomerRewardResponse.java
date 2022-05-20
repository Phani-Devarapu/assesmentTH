package com.phani.exam.models;

import lombok.Value;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Map;

@Value
public class CustomerRewardResponse {
    int customerId;
    String customerName;
    Map<Month, BigDecimal> rewardsByMonth;
    BigDecimal totalRewards;
}
