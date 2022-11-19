package com.cinema.invoicemicroservice.service;

import com.cinema.invoicemicroservice.model.Order;
import com.cinema.invoicemicroservice.model.Payment;
import com.cinema.invoicemicroservice.model.ShowTime;
import com.cinema.invoicemicroservice.repository.OrderRepository;
import com.cinema.invoicemicroservice.repository.PaymentRepository;
import com.cinema.invoicemicroservice.repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.cinema.invoicemicroservice.service.PaymentServiceImpl.unwrapPayment;


@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    ShowTimeRepository showTimeRepository;
    @Override
    public Order getOrderById(Long id) {
        Optional<Order> entity = orderRepository.findById(id);
        return InvoiceServiceImpl.unwrapOrder(entity, id);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order addShowTimeToOrder(Long showTimeId, Long orderId) {
        Order order = getOrderById(orderId);
        Optional<ShowTime> showTime = showTimeRepository.findById(showTimeId);
        if (showTime.isPresent()){
            order.setShowTimes(showTime.get());
            return orderRepository.saveAndFlush(order);
        }
        return null;
    }


    @Override
    public Order addPaymentToOrder(Long paymentId, Long orderId) {
        Optional<Order> entity = orderRepository.findById(orderId);
        Order order = InvoiceServiceImpl.unwrapOrder(entity, orderId);
        Optional<Payment> entityPayment = paymentRepository.findById(orderId);
        Payment payment = unwrapPayment(entityPayment, paymentId);
        order.setPayment(payment);
        return orderRepository.saveAndFlush(order);
    }

    @Override
    public ShowTime getEnrolledShowTime(Long orderId) {
        Optional<Order> entity = orderRepository.findById(orderId);
        Order order = InvoiceServiceImpl.unwrapOrder(entity, orderId);
        return order.getShowTimes();
    }

    @Override
    public Payment getEnrolledPayment(Long orderId) {
        Optional<Order> entity = orderRepository.findById(orderId);
        Order order = InvoiceServiceImpl.unwrapOrder(entity, orderId);
        return order.getPayment();
    }
}
