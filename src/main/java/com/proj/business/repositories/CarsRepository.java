package com.proj.business.repositories;

import com.proj.business.documents.CarDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarsRepository extends MongoRepository<CarDocument,String> {
}
