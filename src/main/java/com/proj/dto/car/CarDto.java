package com.proj.dto.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter

public class CarDto {
    private String id;
    private String make;
    private String model;
    private int Year;
    private double engine;


    public CarDto(String id, String make, String model, int year, double engine) {
        this.id = id;
        this.make = make;
        this.model = model;
        Year = year;
        this.engine = engine;
    }
}

