package de.cts.customerservice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.cts.customerservice.model.Customer;

@SpringBootTest
class CustomerRepoTest {

	@Autowired
	private CustomerRepo repo;

	@Test
	void test() {
		repo.save(new Customer("chris", "ScharnhorstStraße 46", "ctschubel@googlemail.com", 24));
		repo.findAll().stream().forEach(c -> System.out.println(c));
	}

}
