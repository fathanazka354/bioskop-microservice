package com.cinema.invoicemicroservice.service;

import com.cinema.invoicemicroservice.dto.Invoice;
import org.springframework.stereotype.Service;

@Service
public interface InvoiceService {
    Invoice getInvoiceById(Long orderId);
}
