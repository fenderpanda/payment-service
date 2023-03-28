package com.example.paymentservice.repository;

import com.example.paymentservice.entity.TranslationPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TranslationPaymentRepository extends JpaRepository<TranslationPayment, Long> {

}
