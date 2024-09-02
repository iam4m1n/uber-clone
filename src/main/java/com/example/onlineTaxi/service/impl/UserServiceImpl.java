package com.example.onlineTaxi.service.impl;

import com.example.onlineTaxi.model.Users.User.UserDTO;
import com.example.onlineTaxi.model.order.OrderDTO;
import com.example.onlineTaxi.model.payment.PaymentDTO;
import com.example.onlineTaxi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDTO getInfo(Long id) {
        return null;
    }

    @Override
    public OrderDTO addOrder(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public List<OrderDTO> getOrderHistory(Long id) {
        return null;
    }

    @Override
    public List<PaymentDTO> getPaymentHistory(Long id) {
        return null;
    }

    @Override
    public PaymentDTO payment(PaymentDTO paymentDTO) {
        return null;
    }
}
