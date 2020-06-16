package com.proj.security.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Getter @Setter

@Document("account")
public class Account {
    private String name;
    private String lastName;
    @Id
    private String email;
    private String password;
    private Set<String> roles = new HashSet<>();
    private LocalDate dateOfRegistration;
    private Set<String> idCars = new HashSet<>();


    public Account(String name, String lastName, String email, String password, String roles, LocalDate dateOfRegistration) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles.add(roles);
        this.dateOfRegistration = dateOfRegistration;

    }

    public void setIdCars(String idCars) {
        this.idCars.add(idCars);
    }
}
