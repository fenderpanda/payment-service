package com.example.paymentservice.controller;

import com.example.paymentservice.exception.NotEnoughMoneyException;
import com.example.paymentservice.service.TranslationPaymentService;
import com.example.paymentservice.service.UserBalanceService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    private final UserBalanceService userBalanceService;
    private final TranslationPaymentService translationPaymentService;

    public PaymentController(UserBalanceService userBalanceService,
                             TranslationPaymentService translationPaymentService) {
        this.userBalanceService = userBalanceService;
        this.translationPaymentService = translationPaymentService;
    }

    @PostMapping("/payment")
    public long makePayment(
            JwtAuthenticationToken token,
            @RequestParam long fileId,
            @RequestParam int cost
    ) {
        String userId = token.getToken().getSubject();

        if (!userBalanceService.hasEnoughMoney(userId, cost)) {
            throw new NotEnoughMoneyException();
        }

        return translationPaymentService.makePayment(userId, fileId, cost);
    }
}
