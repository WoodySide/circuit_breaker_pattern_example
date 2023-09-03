package com.woody_side.data_consumer.repository;

import com.woody_side.data_consumer.model.Countries;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountriesRepository extends MongoRepository<Countries, String> {
}
