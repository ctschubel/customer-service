package de.cts.customerservice;

import java.util.Collection;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

import de.cts.customerservice.model.Customer;
import de.cts.customerservice.repository.CustomerRepo;

@SpringBootApplication
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

	
	// add sample data
	@Bean
	@ConditionalOnProperty(value = "testData.load", havingValue = "true")
	public CommandLineRunner loadData(final CustomerRepo repository) {
		return (args) -> {
			Collection<Customer> testData = repository.findByLastName("Test");
			if(testData.isEmpty()) {
				repository.save(new Customer("Theo", "Test", "theo@test.de", 20));
				repository.save(new Customer("Thessa", "Test", "thessa@test.de", 24));
				repository.save(new Customer("Timo", "Test", "timo@test.de", 36));
			}
		};
	}
	
}
