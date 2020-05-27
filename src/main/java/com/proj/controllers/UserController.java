package com.proj.controllers;

import com.proj.dto.car.CarDto;
import com.proj.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
@CrossOrigin
@RequestMapping("/actions")
public class UserController {

    @Autowired
    CarService carService;

    @PostMapping("addCar")
    public boolean addCar(@RequestBody CarDto carDto) throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
       return carService.addCar(carDto);
    }
}
