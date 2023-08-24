    package com.example.brettagraphql2.model.input;

    import lombok.Data;

    @Data
    public class AuthEntity {
        private String accessToken;
        private String tokenType = "Bearer ";

        public AuthEntity(String accessToken) {
            this.accessToken = accessToken;
        }
    }
