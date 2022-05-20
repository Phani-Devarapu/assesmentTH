package com.phani.exam;

import com.phani.exam.models.Customer;
import com.phani.exam.models.Purchase;
import com.phani.exam.models.ValuedPurchase;
import com.phani.exam.repository.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class ExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
    }

}
