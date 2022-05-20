package com.phani.exam.support;

import com.phani.exam.models.Customer;
import com.phani.exam.models.CustomerRewardResponse;
import com.phani.exam.models.Purchase;
import com.phani.exam.models.ValuedPurchase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;

public class TestSupport {

    public static final int CUSTOMER_ID = 1;

    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.of(2022, 05, 19,
            00, 00, 00);

    public static final ValuedPurchase VALUED_PURCHASE = new ValuedPurchase(CUSTOMER_ID, BigDecimal.valueOf(130),
            BigDecimal.valueOf(110), LOCAL_DATE_TIME);

    public static final ValuedPurchase VALUED_PURCHASE_2 = new ValuedPurchase(CUSTOMER_ID, BigDecimal.valueOf(150),
            BigDecimal.valueOf(150), LOCAL_DATE_TIME);

    public static final Purchase PURCHASE = new Purchase(BigDecimal.valueOf(130), LOCAL_DATE_TIME);

    public static final Customer CUSTOMER = new Customer(CUSTOMER_ID, "Adam", List.of(PURCHASE));

    public static final CustomerRewardResponse CUSTOMER_REWARD_RESPONSE =
            new CustomerRewardResponse(CUSTOMER_ID, "Adam",
                    Map.of(Month.MAY, BigDecimal.valueOf(130)), BigDecimal.valueOf(130));

}
