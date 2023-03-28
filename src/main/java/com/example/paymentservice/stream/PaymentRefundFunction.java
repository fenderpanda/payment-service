package com.example.paymentservice.stream;

import com.example.paymentservice.payload.MessageFileDto;
import com.example.paymentservice.service.TranslationPaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PaymentRefundFunction {

    private final TranslationPaymentService translationPaymentService;

    public PaymentRefundFunction(TranslationPaymentService translationPaymentService) {
        this.translationPaymentService = translationPaymentService;
    }

    @Bean
    public Function<MessageFileDto, MessageFileDto> onTextExtractionError() {
        return (message) -> {
            translationPaymentService.makeRefund(message.getPaymentId());

            return message;
        };
    }

    @Bean
    public Function<MessageFileDto, MessageFileDto> onFileTranslationError() {
        return (message) -> {
            translationPaymentService.makeRefund(message.getPaymentId());

            return message;
        };
    }
}
