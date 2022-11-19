package com.cinema.invoicemicroservice.service;

import com.cinema.invoicemicroservice.model.Order;
import com.cinema.invoicemicroservice.model.Payment;
import com.cinema.invoicemicroservice.model.ShowTime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Order getOrderById(Long id);
    Order saveOrder(Order order);
    List<Order> getAllOrder();
    void deleteOrder(Long id);
    Order addShowTimeToOrder(Long showTimeId, Long orderId);
    Order addPaymentToOrder(Long paymentId, Long orderId);
    ShowTime getEnrolledShowTime(Long orderId);
    Payment getEnrolledPayment(Long orderId);
}
