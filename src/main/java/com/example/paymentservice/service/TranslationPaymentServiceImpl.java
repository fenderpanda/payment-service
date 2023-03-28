package com.example.paymentservice.service;

import com.example.paymentservice.entity.Status;
import com.example.paymentservice.entity.TranslationPayment;
import com.example.paymentservice.exception.TranslationPaymentNotFoundException;
import com.example.paymentservice.repository.TranslationPaymentRepository;
import org.springframework.stereotype.Service;

@Service
public class TranslationPaymentServiceImpl implements TranslationPaymentService{

    private final TranslationPaymentRepository translationPaymentRepository;
    private final UserBalanceService userBalanceService;

    public TranslationPaymentServiceImpl(TranslationPaymentRepository translationPaymentRepository,
                                         UserBalanceService userBalanceService) {
        this.translationPaymentRepository = translationPaymentRepository;
        this.userBalanceService = userBalanceService;
    }

    @Override
    public long makePayment(String userId, long fileId, int cost) {
        userBalanceService.updateBalance(userId, -cost);

        TranslationPayment translationPayment = new TranslationPayment();

        translationPayment.setUserId(userId);
        translationPayment.setFileId(fileId);
        translationPayment.setStatus(Status.CHARGED);
        translationPayment.setCost(cost);

        return translationPaymentRepository.save(translationPayment).getId();
    }

    @Override
    public void makeRefund(long paymentId) {
        TranslationPayment tp = translationPaymentRepository.findById(paymentId)
                .orElseThrow(TranslationPaymentNotFoundException::new);

        userBalanceService.updateBalance(tp.getUserId(), tp.getCost());

        tp.setStatus(Status.CANCELED);

        translationPaymentRepository.save(tp);
    }
}
