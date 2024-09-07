package com.example.onlineTaxi.service.impl;

import com.example.onlineTaxi.model.AuthenticationResponse;
import com.example.onlineTaxi.model.Users.User.UserDTO;
import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.order.OrderDTO;
import com.example.onlineTaxi.model.order.OrderEntity;
import com.example.onlineTaxi.model.payment.PaymentDTO;
import com.example.onlineTaxi.repository.UserRepository;
import com.example.onlineTaxi.service.JWT.JWTService;
import com.example.onlineTaxi.service.JWT.MyUserDetailService;
import com.example.onlineTaxi.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder(12);

    private final JWTService jwtService;

    private final MyUserDetailService myUserDetailService;




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



    @Override
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String refreshToken = extractJwtFromRequest(request);
        String username;
        if (refreshToken != null && jwtService.validateToken(refreshToken) && jwtService.isRefreshToken(refreshToken)){
            username = jwtService.extractUserName(refreshToken);

            // todo : add id and role claims



            if (username != null){

                UserDetails userDetails = myUserDetailService.loadUserByUsername(username);

                if (jwtService.validateToken(refreshToken, userDetails)){
                    String newAccessToken = jwtService.generateJwtToken(username);
                    String newRefreshToken = jwtService.generateRefreshToken(username);

                    AuthenticationResponse authenticationResponse = AuthenticationResponse
                            .builder()
                            .accessToken(newAccessToken)
                            .refreshToken(newRefreshToken)
                            .build();
                    new ObjectMapper().writeValue(response.getOutputStream(), authenticationResponse);

                }
            }

        }
    }

    @Override
    public OrderEntity travel() {
        return null;
    }

    @Override
    public UserEntity findById(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
