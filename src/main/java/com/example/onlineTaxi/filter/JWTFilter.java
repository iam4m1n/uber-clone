package com.example.onlineTaxi.filter;


import com.example.onlineTaxi.service.JWT.JWTService;
import com.example.onlineTaxi.service.JWT.JWTUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;



    private final JWTUserDetailService jwtUserDetailService;

    public JWTFilter(
            @Qualifier("beanTwo") JWTUserDetailService jwtUserDetailService,
            JWTService jwtService
    ){
        this.jwtService = jwtService;
        this.jwtUserDetailService = jwtUserDetailService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = extractJwtFromRequest(request);
        String username;
        String id = "";
        String Role = "";


//        && !jwtService.isRefreshToken(jwtToken)
        if (jwtToken != null && jwtService.validateToken(jwtToken) && !jwtService.isRefreshToken(jwtToken)){
            username = jwtService.extractUserName(jwtToken);

            // todo : add id and role claims

            String userInfo = username + " " + id + " " + Role;


            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){

                UserDetails userDetails = jwtUserDetailService.loadUserByUsername(userInfo);

                if (jwtService.validateToken(jwtToken, userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }

        }

        filterChain.doFilter(request, response);

    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
