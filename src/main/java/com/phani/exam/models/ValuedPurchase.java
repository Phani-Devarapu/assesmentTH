package com.phani.exam.models;

import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
public class ValuedPurchase {
    int customerId;
    BigDecimal purchaseAmount;
    BigDecimal rewardPointsEarned;
    LocalDateTime purchasedTimeStamp;
}
