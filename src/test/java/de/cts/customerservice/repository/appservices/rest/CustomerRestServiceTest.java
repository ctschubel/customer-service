package de.cts.customerservice.repository.appservices.rest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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

	private List<Customer> testCustomers = List.of(new Customer("theo", "test", "theo@test.de", 20),
			new Customer("thessa", "test", "thessa@test.de", 21));

	@Test
	void getCustomers_ok() throws Exception {
		when(this.customerRepoMock.findAll()).thenReturn(this.testCustomers);

		this.mockMvc.perform(get("/v1/customers"))//
				.andDo(print())//
				.andExpect(status().isOk()).andExpect(jsonPath("$.*", Matchers.hasSize(2)))//
				.andExpect(jsonPath("$[0].firstName", Matchers.is("theo")))//
				.andExpect(jsonPath("$[1].firstName", Matchers.is("thessa")));

	}

	@Test
	void getCustomer_ok() throws Exception {
		this.testCustomers.get(0).setId("1");
		;
		when(this.customerRepoMock.findById("1")).thenReturn(Optional.of(this.testCustomers.get(0)));

		this.mockMvc.perform(get("/v1/customers/1"))//
				.andDo(print())//
				.andExpect(status().isOk())//
				.andExpect(jsonPath("$.*", Matchers.hasSize(5)))//
				.andExpect(jsonPath("$.id", Matchers.is("1")))//
				.andExpect(jsonPath("$.firstName", Matchers.is("theo")))//
				.andExpect(jsonPath("$.lastName", Matchers.is("test")))//
				.andExpect(jsonPath("$.email", Matchers.is("theo@test.de")))//
				.andExpect(jsonPath("$.age", Matchers.is(20)));
	}

	@Test
	void getCustomer_notFound() throws Exception {
		when(this.customerRepoMock.findById("1")).thenReturn(Optional.empty());

		this.mockMvc.perform(get("/v1/customers/1"))//
				.andDo(print())//
				.andExpect(status().isNotFound());
	}

	@Test
	void createCustomer_ok() throws Exception {
		this.mockMvc
				.perform(post("/v1/customers").contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(this.testCustomers.get(0).toString()))//
				.andExpect(status().isOk());

		verify(this.customerRepoMock).save(this.testCustomers.get(0));
	}

	@Test
	void deleteCustomer_ok() throws Exception {
		this.mockMvc.perform(delete("/v1/customers/1"))//
				.andExpect(status().isOk());

		verify(this.customerRepoMock).deleteById("1");
	}

}
