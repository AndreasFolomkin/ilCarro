package com.proj.services;

import com.proj.documents.Account;
import com.proj.documents.CarDocument;
import com.proj.dto.car.CarDto;
import com.proj.repositories.AccountRepository;
import com.proj.repositories.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


import java.lang.reflect.InvocationTargetException;


@Service
public class CarService {

   @Autowired
    AccountRepository accountRepository;
    @Autowired
    CarsRepository carsRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addCar(CarDto carDto) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        CarDocument carDocument = carsRepository.findById(carDto.getId()).orElse(null);
        if (carDocument != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id is exist");

        carDocument = new CarDocument(carDto);
        carDocument.setOwner(getUserNameFromContext());
        carsRepository.save(carDocument);
        Account account= accountRepository.findById(carDocument.getOwner()).orElse(null);
        account.setIdCars(carDto.getId());
       accountRepository.save(account);



        return true;
    }
    private String getUserNameFromContext(){
        String user =  SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(user);
        return user;
    }
}

