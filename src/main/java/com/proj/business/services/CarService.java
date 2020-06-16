package com.proj.business.services;

import com.proj.security.documents.Account;
import com.proj.business.documents.CarDocument;
import com.proj.business.dto.car.CarDto;
import com.proj.security.repositories.AccountRepository;
import com.proj.business.repositories.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;




@Service
public class CarService {

   @Autowired
    AccountRepository accountRepository;
    @Autowired
    CarsRepository carsRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addCar(CarDto carDto)  {
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

