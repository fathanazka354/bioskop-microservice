package com.cinema.invoicemicroservice.repository;

import com.cinema.invoicemicroservice.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
