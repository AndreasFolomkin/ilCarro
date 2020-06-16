package com.proj.controllers;


import com.proj.security.dto.AccountDto;
import com.proj.security.dto.LoginDto;
import com.proj.security.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
