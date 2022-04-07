package com.sai.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AuthToken {
    private String token;

    public AuthToken(){

    }

    public AuthToken(String token) {
        this.token = token;
    }
}
