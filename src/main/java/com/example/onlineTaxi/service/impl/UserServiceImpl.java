package com.example.onlineTaxi.service.impl;

import com.example.onlineTaxi.model.AuthenticationResponse;
import com.example.onlineTaxi.model.Users.User.UserDTO;
import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.order.OrderDTO;
import com.example.onlineTaxi.model.payment.PaymentDTO;
import com.example.onlineTaxi.repository.UserRepository;
import com.example.onlineTaxi.service.JWT.JWTService;
import com.example.onlineTaxi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder(12);

    private final JWTService jwtService;




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

    public UserEntity register(UserEntity userEntity){
        // todo : set the password encoder to bCrypt.encode(userEntity.getPassword()
        userEntity.setPassword(bCrypt.encode(userEntity.getPassword()));
        UserEntity savedUser = userRepository.save(userEntity);
        return savedUser;
    }public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public AuthenticationResponse verify(UserEntity userEntity) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userEntity.getUsername(), userEntity.getPassword()));
        if (authentication.isAuthenticated()) {

            String accessToken = jwtService.generateJwtToken(userEntity.getUsername());
            String refreshToken = jwtService.generateRefreshToken(userEntity.getUsername());


            return AuthenticationResponse
                    .builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .build();
        }

        return AuthenticationResponse.builder().accessToken("fail").build();
    }

}
