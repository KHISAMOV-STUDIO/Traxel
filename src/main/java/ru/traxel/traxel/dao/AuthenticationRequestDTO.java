package ru.traxel.traxel.dao;
import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String email;
    private String password;
}