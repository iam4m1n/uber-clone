package com.example.onlineTaxi.service.JWT;

import com.example.onlineTaxi.enums.Role;
import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.Users.User.UserPrincipal;
import com.example.onlineTaxi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("beanOne")
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user1 = userRepository.findByUsername(username);

        if (user1 == null){
            throw new UsernameNotFoundException("no user found!");
        }

        return new UserPrincipal(user1);

//
//        return new UserPrincipal(
//                new UserEntity(null, username, null, null, null, null, Role.USER)
//        );
    }


}
