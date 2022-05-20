package com.phani.exam.repository;

import com.phani.exam.models.ValuedPurchase;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.phani.exam.support.TestSupport.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerRepoTest {

    private CustomerRepo customerRepo;

    @BeforeAll
    void setUp() {
        this.customerRepo = new CustomerRepo();
    }

    @Test
    void addNewPurchaseTest() {

        this.customerRepo.emptyPurchaseMap();

        final List<ValuedPurchase> valuedPurchases = this.customerRepo.getValuedPurchases(CUSTOMER_ID);
        assertEquals(valuedPurchases.size(), 0);

        this.customerRepo.addNewPurchase(CUSTOMER_ID, VALUED_PURCHASE);

        final List<ValuedPurchase> valuedPurchasesUpdated = this.customerRepo.getValuedPurchases(CUSTOMER_ID);
        assertEquals(valuedPurchasesUpdated.size(), 1);
    }

    @Test
    void getValuedPurchasesTest() {

        this.customerRepo.emptyPurchaseMap();

        final List<ValuedPurchase> valuedPurchases = this.customerRepo.getValuedPurchases(CUSTOMER_ID);
        assertEquals(valuedPurchases.size(), 0);

        this.customerRepo.addNewPurchase(CUSTOMER_ID, VALUED_PURCHASE);
        this.customerRepo.addNewPurchase(CUSTOMER_ID, VALUED_PURCHASE_2);

        final List<ValuedPurchase> valuedPurchasesUpdated = this.customerRepo.getValuedPurchases(CUSTOMER_ID);
        assertThat(valuedPurchasesUpdated).hasSameElementsAs(List.of(VALUED_PURCHASE, VALUED_PURCHASE_2));
    }
}