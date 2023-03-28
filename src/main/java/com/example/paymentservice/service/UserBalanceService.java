package com.example.paymentservice.service;

public interface UserBalanceService {
    int getUserBalance(String userId);
    void updateBalance(String userId, int cost);
    boolean hasEnoughMoney(String userId, int cost);
}
