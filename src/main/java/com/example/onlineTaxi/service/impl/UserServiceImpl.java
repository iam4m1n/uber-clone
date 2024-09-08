package com.example.onlineTaxi.service.impl;

import com.example.onlineTaxi.microservices.MscService;
import com.example.onlineTaxi.model.AuthenticationResponse;
import com.example.onlineTaxi.model.Users.User.UserDTO;
import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.Users.UserMapper;
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
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder(12);

    private final JWTService jwtService;

    private final MyUserDetailService myUserDetailService;

    private final MscService mscService;


    public UserEntity register(UserEntity userEntity){
        // todo : set the password encoder to bCrypt.encode(userEntity.getPassword()
        userEntity.setPassword(bCrypt.encode(userEntity.getPassword()));

        mscService.sendTransaction("new user registered at " + new Date());

        return userRepository.save(userEntity);
    }


    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserMapper::userToUserDto).collect(Collectors.toList());
    }

    public AuthenticationResponse verify(String username, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        if (authentication.isAuthenticated()) {

            String accessToken = jwtService.generateJwtToken(username);
            String refreshToken = jwtService.generateRefreshToken(password);

            mscService.sendTransaction("User with username: " + username + " has been logged in at " + new Date());
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
            if (username != null){

                UserDetails userDetails = myUserDetailService.loadUserByUsername(username);

                if (jwtService.validateToken(refreshToken, userDetails)){
                    String newAccessToken = jwtService.generateJwtToken(username);
                    String newRefreshToken = jwtService.generateRefreshToken(username);

                    mscService.sendTransaction("new access and refresh token made for user: " + username);

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
    public UserEntity findById(Long userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    @Override
    public UserDTO getUserById(Long userId) {
        return UserMapper.userToUserDto(userRepository.findById(userId).orElseThrow());
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
        mscService.sendTransaction("User with id: " + userId + " has been deleted");
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
