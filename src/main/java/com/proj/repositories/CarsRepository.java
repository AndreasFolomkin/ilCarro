package com.proj.repositories;

import com.proj.documents.CarDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarsRepository extends MongoRepository<CarDocument,String> {
}
