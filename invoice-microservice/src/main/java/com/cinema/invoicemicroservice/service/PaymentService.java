package com.cinema.invoicemicroservice.service;

import com.cinema.invoicemicroservice.model.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    Payment getPaymentById(Long id);
    Payment savePayment(Payment payment);
    List<Payment> getAllPayment();
    void deletePayment(Long id);
}
