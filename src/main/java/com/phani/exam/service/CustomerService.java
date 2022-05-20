package com.phani.exam.service;

import com.phani.exam.models.Customer;
import com.phani.exam.models.CustomerRewardResponse;
import com.phani.exam.models.Purchase;
import com.phani.exam.models.ValuedPurchase;
import com.phani.exam.repository.CustomerRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerService {

    private final Integer rewardPointsOver100;
    private final Integer rewardPointsOver50;
    private final CustomerRepo customerRepo;

    public CustomerService(@Value("${rewards.forEveryHundred}") final Integer rewardPointsOver100,
                           @Value("${rewards.forEveryFifty}") final Integer rewardPointsOver50,
                           final CustomerRepo customerRepo) {
        this.rewardPointsOver100 = rewardPointsOver100;
        this.rewardPointsOver50 = rewardPointsOver50;
        this.customerRepo = customerRepo;
    }

    public CustomerRewardResponse getCustomerRewards(final int customerId) {

        log.info("the vales are {}, {}", rewardPointsOver100, rewardPointsOver50);

        final List<ValuedPurchase> valuedPurchases = this.customerRepo.getValuedPurchases(customerId);
        final Map<Month, BigDecimal> rewardsByMonth = this.calculateRewardPointsByMonth(valuedPurchases);
        final BigDecimal totalRewards = rewardsByMonth.values().stream().reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
        final Customer customerInfo = this.customerRepo.getCustomerInfo(customerId);
        return new CustomerRewardResponse(customerId, customerInfo.getName(), rewardsByMonth, totalRewards);
    }

    public void addNewPurchase(final int customerId, final Purchase purchase) {
        final ValuedPurchase valuedPurchase = new ValuedPurchase(
                customerId,
                purchase.getPurchaseAmount(),
                this.calculateRewards(purchase.getPurchaseAmount()),
                purchase.getPurchasedTimeStamp()
        );
        this.customerRepo.addNewPurchase(customerId, valuedPurchase);
    }

    private BigDecimal calculateRewards(final BigDecimal amount) {

        int comparedResult = amount.compareTo(BigDecimal.valueOf(100));

        if (comparedResult > 0) {
            final BigDecimal amountRewards = amount.subtract(BigDecimal.valueOf(100));
            return amountRewards.multiply(BigDecimal.valueOf(rewardPointsOver100))
                    .add(new BigDecimal(50));
        } else {
            final BigDecimal amountRewards = amount.subtract(BigDecimal.valueOf(50));
            return amountRewards.multiply(BigDecimal.valueOf(rewardPointsOver50));
        }
    }


    private Map<Month, BigDecimal> calculateRewardPointsByMonth(final List<ValuedPurchase> valuedPurchase) {

        final Map<Month, List<ValuedPurchase>> purchaseByMonth =
                valuedPurchase.stream()
                        .collect(Collectors.groupingBy(purchase -> purchase.getPurchasedTimeStamp().getMonth()));

        return purchaseByMonth.entrySet().stream()
                .map(entry -> {
                    BigDecimal sumPerMonth = entry.getValue().stream()
                            .map(ValuedPurchase::getRewardPointsEarned)
                            .reduce(BigDecimal::add)
                            .orElse(BigDecimal.ZERO);
                    return Pair.of(entry.getKey(), sumPerMonth);
                }).collect(Collectors.toMap(Pair::getKey, Pair::getValue));

    }
}
