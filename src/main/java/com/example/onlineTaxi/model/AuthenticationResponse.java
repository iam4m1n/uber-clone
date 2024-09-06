package com.example.onlineTaxi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse {


    @JsonProperty("jwt token")
    private String accessToken;

    @JsonProperty("refresh token")
    private String refreshToken;

}
