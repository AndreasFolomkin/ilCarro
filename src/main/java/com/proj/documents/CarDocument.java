package com.proj.documents;

import com.proj.dto.car.CarDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@Getter @Setter
@Document("cars")
@Data

public class CarDocument {
@Id
    private String id;
    private String make;
    private String model;
    private int Year;
    private double engine;
    private String owner;

    public CarDocument(String id, String make, String model, int year, double engine, String owner) {
        this.id = make+model+owner;
        this.make = make;
        this.model = model;
        Year = year;
        this.engine = engine;
        this.owner = owner;
    }
    public CarDocument(CarDto carDto){
        this.id = carDto.getId();
        this.make = carDto.getMake();
        this.model = carDto.getModel();
        this.Year = carDto.getYear();
        this.engine = carDto.getEngine();
    }
}


