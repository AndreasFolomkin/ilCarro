package com.proj.dto.registration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class AccountDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
