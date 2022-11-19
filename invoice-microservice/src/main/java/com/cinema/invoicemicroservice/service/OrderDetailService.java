package com.cinema.invoicemicroservice.service;

import com.cinema.invoicemicroservice.model.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailService {
    OrderDetail getOrderDetailById(Long id);
}
