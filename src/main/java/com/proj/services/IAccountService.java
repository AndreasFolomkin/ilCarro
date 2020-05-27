package com.proj.services;

import com.proj.dto.registration.LoginDto;
import org.springframework.http.ResponseEntity;

public interface IAccountService {
    boolean addAccount(String name, String lastName, String email, String password);
    public ResponseEntity<?> login(LoginDto loginDto);
}
