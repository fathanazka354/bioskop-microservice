package com.cinema.invoicemicroservice.repository;

import com.cinema.invoicemicroservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
