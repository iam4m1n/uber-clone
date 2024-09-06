package com.example.onlineTaxi.service;

import com.example.onlineTaxi.model.AuthenticationResponse;
import com.example.onlineTaxi.model.Users.User.UserDTO;
import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.order.OrderDTO;
import com.example.onlineTaxi.model.payment.PaymentDTO;

import java.util.List;

public interface UserService {

    public UserDTO getInfo(Long id);

    public OrderDTO addOrder(OrderDTO orderDTO);

    public List<OrderDTO> getOrderHistory(Long id);

    public List<PaymentDTO> getPaymentHistory(Long id);

    public PaymentDTO payment(PaymentDTO paymentDTO);


    AuthenticationResponse verify(UserEntity userEntity);
}
