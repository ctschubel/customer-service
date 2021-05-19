package de.cts.customerservice.repository.appservices.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import de.cts.customerservice.appservices.rest.CustomerRestService;
import de.cts.customerservice.model.Customer;
import de.cts.customerservice.repository.CustomerRepo;

/**
 * @author ctschubel
 *
 */
@WebMvcTest(CustomerRestService.class)
public class CustomerRestServiceTest {

	@MockBean
	private CustomerRepo customerRepoMock;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getCustomers_ok() throws Exception {
		when(customerRepoMock.findAll()).thenReturn(List.of(new Customer("theo", "test", "theo@test.de", 20),
				new Customer("thessa", "test", "thessa@test.de", 21)));
		this.customerRepoMock.findAll().forEach(customer -> System.out.println(customer));
		this.mockMvc.perform(get("/v1/customers"))//
				.andDo(print())//
				.andExpect(jsonPath("$.*", Matchers.hasSize(2)))//
				.andExpect(jsonPath("$[0].firstName", Matchers.is("theo")))//
				.andExpect(jsonPath("$[1].firstName", Matchers.is("thessa")));

	}
	
	//TODO add tests
}
