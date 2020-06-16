package com.proj.controllers;

import com.proj.business.dto.car.CarDto;
import com.proj.business.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/actions")
public class UserController {

    @Autowired
    CarService carService;

    @PostMapping("addCar")
    public boolean addCar(@RequestBody CarDto carDto)  {
       return carService.addCar(carDto);
    }
}
