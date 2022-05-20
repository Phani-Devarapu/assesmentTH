package com.phani.exam.service;

import com.phani.exam.models.Customer;
import com.phani.exam.models.CustomerRewardResponse;
import com.phani.exam.models.Purchase;
import com.phani.exam.models.ValuedPurchase;
import com.phani.exam.repository.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private static final int CUSTOMER_ID = 1;
    private static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.of(2022, 05, 19,
            00, 00, 00);
    private static final ValuedPurchase VALUED_PURCHASE = new ValuedPurchase(CUSTOMER_ID, BigDecimal.valueOf(130),
            BigDecimal.valueOf(110), LOCAL_DATE_TIME);
    private static final Purchase PURCHASE = new Purchase(BigDecimal.valueOf(130), LOCAL_DATE_TIME);
    private static final Customer CUSTOMER = new Customer(CUSTOMER_ID, "Adam", List.of(PURCHASE));


    @Mock
    private CustomerRepo customerRepo;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void addNewPurchaseTest() {
        this.customerService.addNewPurchase(CUSTOMER_ID, PURCHASE);
        verify(this.customerRepo, times(1)).addNewPurchase(CUSTOMER_ID, VALUED_PURCHASE);
    }

    @Test
    void getCustomerRewardsTest() {

        when(this.customerRepo.getValuedPurchases(CUSTOMER_ID)).thenReturn(List.of(VALUED_PURCHASE));
        when(this.customerRepo.getCustomerInfo(CUSTOMER_ID)).thenReturn(CUSTOMER);
        final CustomerRewardResponse customerRewards = this.customerService.getCustomerRewards(CUSTOMER_ID);

        verify(this.customerRepo, times(1)).getValuedPurchases(CUSTOMER_ID);
        verify(this.customerRepo, times(1)).getCustomerInfo(CUSTOMER_ID);

        assertEquals(customerRewards.getTotalRewards(), BigDecimal.valueOf(110));
        assertEquals(customerRewards.getRewardsByMonth(), Map.of(Month.MAY, BigDecimal.valueOf(110)));
    }

}