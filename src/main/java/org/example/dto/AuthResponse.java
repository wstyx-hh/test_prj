package org.example.dto;

public class AuthResponse {
    private String accessToken;
    private String refreshToken;

    public AuthResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }


    //для получение доступа. можнно было добавить фронт но лень. пусть будет
    public String getAccessToken() {
        return accessToken;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
}
