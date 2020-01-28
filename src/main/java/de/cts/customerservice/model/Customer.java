package de.cts.customerservice.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.Id;

/**
 * @author ctschubel
 *
 */
public class Customer {

	private String name, address, email;
	private int age;
	@Id
	private String id;

	public Customer() {
	}

	public Customer(String name, String address, String email, int age) {
		super();
		this.name = name;
		this.address = address;
		this.email = email;
		this.age = age;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

}
