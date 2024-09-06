package com.example.onlineTaxi.component;

import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class postConstruct {

    private final UserRepository userRepository;


    @PostConstruct
    public void addUser(){

        UserEntity u1 = new UserEntity(1L, "Am1n", null, null, "1111", null, null);

        userRepository.save(u1);
    }

}
