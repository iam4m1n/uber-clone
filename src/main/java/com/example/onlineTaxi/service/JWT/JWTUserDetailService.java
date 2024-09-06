package com.example.onlineTaxi.service.JWT;
import com.example.onlineTaxi.enums.Role;
import com.example.onlineTaxi.model.Users.User.UserEntity;
import com.example.onlineTaxi.model.Users.User.UserPrincipal;
import com.example.onlineTaxi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Qualifier("beanTwo")
public class JWTUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userInfo) throws UsernameNotFoundException {

        String username = userInfo.split(" ")[0];

        return new UserPrincipal(
                new UserEntity(null, username, null, null, null, null, Role.USER)
        );
    }


}
