package de.cts.customerservice.repository;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;

import de.cts.customerservice.model.Customer;

public interface CustomerRepo extends MongoRepository<Customer, String> {

	String deleteByLastName(String lastName);
	
	Collection<Customer> findByLastName(String lastName);
	
}
