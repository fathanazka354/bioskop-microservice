package com.cinema.invoicemicroservice.service;

import com.cinema.invoicemicroservice.dto.Invoice;
import com.cinema.invoicemicroservice.exception.DataNotFoundException;
import com.cinema.invoicemicroservice.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class InvoiceServiceImpl implements InvoiceService{
    @Autowired
    OrderService orderService;

    @Override
    public Invoice getInvoiceById(Long orderId) {
        Invoice invoice = new Invoice();
        Optional<Order> entity = Optional.ofNullable(orderService.getOrderById(orderId));
        Order order = unwrapOrder(entity, orderId);
        invoice.setFirstName(order.getCustomer().getFirstName());
        invoice.setCustomerId(order.getCustomer().getCustomerId());
        invoice.setSeatCode(order.getShowTimes().getSeat().getSeatCode());
        invoice.setCash(order.getPayment().getCash());
        invoice.setMovieName(order.getShowTimes().getMovie().getMovieName());
        invoice.setDateShowtime(order.getShowTimes().getDateShowtime());
        return invoice;
    }

    static Order unwrapOrder(Optional<Order> entity, Long id){
        if (entity.isPresent()) return entity.get();
        throw new DataNotFoundException(id);
    }

}
