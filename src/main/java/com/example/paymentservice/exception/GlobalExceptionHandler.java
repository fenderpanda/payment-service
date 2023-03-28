package com.example.paymentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotEnoughMoneyException.class})
    public ResponseEntity<String> notEnoughMoneyExceptionHandler() {
        return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED)
                .body("You don't have enough money");
    }

    @ExceptionHandler({TranslationPaymentNotFoundException.class, UserBalanceNotFoundException.class})
    public ResponseEntity<String> translationPaymentNotFoundExceptionHandler() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Something went wrong");
    }
}
