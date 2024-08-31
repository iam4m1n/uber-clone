package com.example.onlineTaxi.service;

import com.example.onlineTaxi.model.User.normalUser.NormalUserDTO;
import com.example.onlineTaxi.model.order.OrderDTO;
import com.example.onlineTaxi.model.payment.PaymentDTO;

import java.util.List;

public interface UserService {

    public NormalUserDTO getInfo(Long id);

    public OrderDTO addOrder(OrderDTO orderDTO);

    public List<OrderDTO> getOrderHistory(Long id);

    public List<PaymentDTO> getPaymentHistory(Long id);

    public PaymentDTO payment(PaymentDTO paymentDTO);


}
