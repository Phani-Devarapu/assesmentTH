package com.phani.exam.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Customer {
    int id;
    String name;
    List<Purchase> listOfPurchases;
}
