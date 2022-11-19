package com.cinema.invoicemicroservice.repository;

import com.cinema.invoicemicroservice.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
