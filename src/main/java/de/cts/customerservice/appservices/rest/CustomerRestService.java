package de.cts.customerservice.appservices.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import de.cts.customerservice.model.Customer;
import de.cts.customerservice.repository.CustomerRepo;

@RestController
@RequestMapping("v1")
public class CustomerRestService {

	@Autowired
	private CustomerRepo repository;

	@GetMapping("customers")
	public List<Customer> getCustomers() {
		return this.repository.findAll();
	}
	
	@GetMapping("customers/{id}")
	public Customer getCustomer(@PathVariable String id) {
		return this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
}
