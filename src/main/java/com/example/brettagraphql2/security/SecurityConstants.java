package com.example.brettagraphql2.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class SecurityConstants {
    public static final long JWT_EXPIRATION = 2592000;

    public static String JWT_SECRET;

    @Value("${apiKey}")
    public void setJwtSecret(String apiKey){
        SecurityConstants.JWT_SECRET = apiKey;
    }
}
