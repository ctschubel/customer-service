package de.cts.customerservice.appservices.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping(value = "customers", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> getCustomers() {
		return this.repository.findAll();
	}

	@GetMapping(value = "customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer getCustomer(@PathVariable final String id) {
		return this.repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping(value = "customers", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createCustomer(@RequestBody final Customer customer) {
		this.repository.save(customer);
	}

	@DeleteMapping("customers/{id}")
	public void deleteCustomer(@PathVariable final String id) {
		this.repository.deleteById(id);
	}
}
