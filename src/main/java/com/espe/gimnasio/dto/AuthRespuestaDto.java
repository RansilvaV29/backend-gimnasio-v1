package com.espe.gimnasio.dto;

public class AuthRespuestaDto {
	 private String token;

    public AuthRespuestaDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
