package com.proj.security.services;


import com.proj.security.dto.LoginDto;
import org.springframework.http.ResponseEntity;

public interface IAccountService {
    boolean addAccount(String name, String lastName, String email, String password);
    public ResponseEntity<?> login(LoginDto loginDto);
}
