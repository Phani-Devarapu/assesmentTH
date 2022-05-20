package com.phani.exam.controller;

import com.phani.exam.models.CustomerRewardResponse;
import com.phani.exam.models.Purchase;
import com.phani.exam.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rewards")
public class MainController {


    private final CustomerService customerService;

    @GetMapping("/get/customer/{id}")
    public ResponseEntity<CustomerRewardResponse> getCustomerRewards(@PathVariable() final String id) {
        log.info("GET: Customer with id {} requested rewards", id);
        final CustomerRewardResponse customerRewards = this.customerService.getCustomerRewards(Integer.parseInt(id));
        log.info("GET: Request completed : {}", customerRewards);

        if (customerRewards != null) {
            return ResponseEntity.ok(customerRewards);
        } else {
            return ResponseEntity.notFound().build();

        }

    }

    @PostMapping("/new/purchase/customer/{customerId}")
    public ResponseEntity<CustomerRewardResponse> addNewPurchase(@PathVariable() final String customerId,
                                                                 @Valid @RequestBody final Purchase purchase) {

        log.info("POST: Request to add purchase {} to the customer {}", purchase, customerId);
        this.customerService.addNewPurchase(Integer.parseInt(customerId), purchase);
        log.info("Successfully added purchase: {} to the customer {}", purchase, customerId);
        return ResponseEntity.ok().build();
    }
}
