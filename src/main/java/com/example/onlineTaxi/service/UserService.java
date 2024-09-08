package com.example.onlineTaxi.service;

import com.example.onlineTaxi.model.AuthenticationResponse;
import com.example.onlineTaxi.model.Users.User.UserDTO;
import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.order.OrderDTO;
import com.example.onlineTaxi.model.order.OrderEntity;
import com.example.onlineTaxi.model.payment.PaymentDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface UserService {

    AuthenticationResponse verify(String username, String password);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

    UserEntity findById(Long userId);

    UserDTO getUserById(Long userId);

    void deleteUserById(Long userId);
}
