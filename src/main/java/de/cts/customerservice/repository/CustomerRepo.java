package de.cts.customerservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.cts.customerservice.model.Customer;

public interface CustomerRepo extends MongoRepository<Customer, String> {

}
