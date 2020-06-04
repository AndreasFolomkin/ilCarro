package com.proj.services;

import com.proj.documents.Account;
import com.proj.dto.registration.LoginDto;
import com.proj.repositories.AccountRepository;
import com.proj.security.jwt.JWTTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;


@Service
public class AccountService implements IAccountService {
  @Autowired
    PasswordEncoder encoder;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTTokenUtils jwtTokenUtils;

 @Override
    public boolean addAccount(String name, String lastName, String email, String password){
     if (accountRepository.existsById(email))throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"account is exist");
        Account account = new Account(name,lastName,email,encoder.encode(password), "ROLE_USER", LocalDate.now());
        accountRepository.save(account);
        return true;
    }
 @Override
    public ResponseEntity<?> login(LoginDto loginDto){
     try {
         Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
         String token = jwtTokenUtils.generateToken(authentication);
         return ResponseEntity.ok(token);
     }catch (BadCredentialsException e) {
         throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"login or password is not correct");
     }
    }
}
