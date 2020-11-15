package de.cts.customerservice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.annotation.DirtiesContext;

import de.cts.customerservice.model.Customer;

/**
 * @author chris
 */
@DataMongoTest
class CustomerRepoTest {

	@Autowired
	private CustomerRepo repo;

	@Test
	public void save_one_ok() {
		Customer customer = new Customer("chris", "ScharnhorstStraße 46", "ctschubel@googlemail.com", 24);
		this.repo.save(customer);
		List<Customer> customers = this.repo.findAll();

		assertThat(customers.get(0)).isEqualTo(customer);
	}

	@Test
	@DirtiesContext
	public void save_multiple_ok() {
		List<Customer> customers = IntStream.range(0, 100)
				.mapToObj(i -> new Customer("chris", "ScharnhorstStraße 46", "ctschubel@googlemail.com", 24))
				.collect(Collectors.toList());
		this.repo.saveAll(customers);
		List<Customer> actualCustomers = this.repo.findAll();

		assertThat(actualCustomers).isEqualTo(customers);
	}

}
