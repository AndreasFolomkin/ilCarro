package com.proj.controllers;

import com.proj.dto.registration.AccountDto;
import com.proj.dto.registration.LoginDto;
import com.proj.security.jwt.JWTTokenUtils;
import com.proj.services.AccountService;
import com.proj.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/registration")
@CrossOrigin
public class AccountController {
    @Autowired
    IAccountService accountService;



    @PostMapping("/addAccount")
    public boolean addAccount(@RequestBody AccountDto accountDto){
       return accountService.addAccount(accountDto.getFirstName(),accountDto.getLastName(),accountDto.getEmail(),accountDto.getPassword());
    }
    @PostMapping("/login")
    public ResponseEntity<?> logIn(@RequestBody LoginDto loginDto){
      return accountService.login(loginDto);
    }
}
