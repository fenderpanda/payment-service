package com.example.paymentservice.service;

public interface TranslationPaymentService {
    long makePayment(String userId, long fileId, int cost);
    void makeRefund(long paymentId);
}
