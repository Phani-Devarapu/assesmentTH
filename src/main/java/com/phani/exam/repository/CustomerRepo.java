package com.phani.exam.repository;

import com.phani.exam.models.Customer;
import com.phani.exam.models.Purchase;
import com.phani.exam.models.ValuedPurchase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class CustomerRepo {

    private ConcurrentHashMap<Integer, Customer> customerMap = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer, List<ValuedPurchase>> purchaseMap = new ConcurrentHashMap<>();

    public void addNewPurchase(final int customerId, final ValuedPurchase valuedPurchase) {

        log.info("Attempting to add new valuedPurchase: {}", valuedPurchase);

        if (purchaseMap.containsKey(customerId)) {
            final List<ValuedPurchase> purchases = purchaseMap.get(customerId);
            List<ValuedPurchase> newList = new ArrayList<>(purchases);
            newList.add(valuedPurchase);
            purchaseMap.put(customerId, newList);
            purchaseMap.entrySet().forEach(entry -> log.debug("{}", entry));
            log.info("Added valuedPurchase to the purchaseMap");
        } else {
            purchaseMap.putIfAbsent(customerId, List.of(valuedPurchase));
        }
    }

    public List<ValuedPurchase> getValuedPurchases(final int customerId) {
        if (purchaseMap.containsKey(customerId)) {
            return this.purchaseMap.get(customerId);
        }
        return Collections.emptyList();
    }

    public void addNewCustomer(final Customer customer) {
        this.customerMap.put(customer.getId(), customer);
    }

    public Customer getCustomerInfo(final int customerId) {
        if (customerMap.containsKey(customerId)) {
            return this.customerMap.get(customerId);
        }
        return null;
    }

    public void emptyCustomerMap() {
        this.customerMap.clear();
    }

    public void emptyPurchaseMap() {
        this.purchaseMap.clear();
    }

    @PostConstruct
    private void loadData() {
        //Initializing Purchases
        final Purchase purchase_1 = new Purchase(BigDecimal.valueOf(120), LocalDateTime.of(2022, 05,
                18, 15, 00, 00));
        final Purchase purchase_2 = new Purchase(BigDecimal.valueOf(130), LocalDateTime.of(2022, 04,
                18, 15, 00, 00));
        final Purchase purchase_3 = new Purchase(BigDecimal.valueOf(140), LocalDateTime.of(2022, 03,
                18, 15, 00, 00));

        final Purchase purchase_4 = new Purchase(BigDecimal.valueOf(150), LocalDateTime.of(2022, 02,
                18, 15, 00, 00));
        final Purchase purchase_5 = new Purchase(BigDecimal.valueOf(200), LocalDateTime.of(2022, 02,
                18, 15, 00, 00));

        final Purchase purchase_6 = new Purchase(BigDecimal.valueOf(500), LocalDateTime.of(2022, 01,
                18, 15, 00, 00));
        final Purchase purchase_7 = new Purchase(BigDecimal.valueOf(10), LocalDateTime.of(2022, 01,
                18, 15, 00, 00));

        //Initializing  customers
        final Customer customer_1 = new Customer(1, "Adam", List.of(purchase_1, purchase_2, purchase_3));
        final Customer customer_2 = new Customer(2, "Mapple", List.of(purchase_4, purchase_5));
        final Customer customer_3 = new Customer(3, "Xerox", List.of(purchase_6, purchase_7));

        //Adding To Map
        this.addNewCustomer(customer_1);
        this.addNewCustomer(customer_2);
        this.addNewCustomer(customer_3);


        //Initializing  valuedPurchases
        final ValuedPurchase valuesPurchase_1 = new ValuedPurchase(1, BigDecimal.valueOf(120), BigDecimal.valueOf(90), LocalDateTime.of(2022, 05, 18, 15, 00, 00));
        final ValuedPurchase valuesPurchase_2 = new ValuedPurchase(1, BigDecimal.valueOf(130), BigDecimal.valueOf(110), LocalDateTime.of(2022, 04, 18, 15, 00, 00));
        final ValuedPurchase valuesPurchase_3 = new ValuedPurchase(1, BigDecimal.valueOf(140), BigDecimal.valueOf(130), LocalDateTime.of(2022, 03, 18, 15, 00, 00));
        ;
        final ValuedPurchase valuesPurchase_4 = new ValuedPurchase(2, BigDecimal.valueOf(150), BigDecimal.valueOf(150), LocalDateTime.of(2022, 02, 18, 15, 00, 00));
        final ValuedPurchase valuesPurchase_5 = new ValuedPurchase(2, BigDecimal.valueOf(200), BigDecimal.valueOf(250), LocalDateTime.of(2022, 02, 18, 15, 00, 00));

        final ValuedPurchase valuesPurchase_6 = new ValuedPurchase(3, BigDecimal.valueOf(500), BigDecimal.valueOf(850), LocalDateTime.of(2022, 01, 18, 15, 00, 00));
        final ValuedPurchase valuesPurchase_7 = new ValuedPurchase(3, BigDecimal.valueOf(10), BigDecimal.valueOf(0), LocalDateTime.of(2022, 01, 18, 15, 00, 00));

        this.addNewPurchase(1, valuesPurchase_1);
        this.addNewPurchase(1, valuesPurchase_2);
        this.addNewPurchase(1, valuesPurchase_3);

        this.addNewPurchase(2, valuesPurchase_4);
        this.addNewPurchase(2, valuesPurchase_5);

        this.addNewPurchase(3, valuesPurchase_6);
        this.addNewPurchase(3, valuesPurchase_7);
    }
}