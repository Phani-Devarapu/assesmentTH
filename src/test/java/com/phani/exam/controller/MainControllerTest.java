package com.phani.exam.controller;

import com.phani.exam.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.phani.exam.support.TestSupport.CUSTOMER_ID;
import static com.phani.exam.support.TestSupport.CUSTOMER_REWARD_RESPONSE;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MainControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private MainController mainController;

    @Test
    void getCustomerRewardsTest() {
        when(this.customerService.getCustomerRewards(CUSTOMER_ID)).thenReturn(CUSTOMER_REWARD_RESPONSE);
        this.mainController.getCustomerRewards(String.valueOf(CUSTOMER_ID));
        verify(this.customerService, times(1)).getCustomerRewards(CUSTOMER_ID);
    }

}