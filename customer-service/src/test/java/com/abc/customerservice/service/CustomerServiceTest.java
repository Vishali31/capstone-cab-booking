package com.abc.customerservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import com.abc.customerservice.dao.CustomerDao;
import com.abc.customerservice.entity.Customer;

@SpringBootTest
public class CustomerServiceTest {

	@Mock
	private CustomerDao customerDao;

	@InjectMocks
	private CustomerServiceImpl customerService;
	
	@Test
	public void testSaveBooking() {
		Customer customerToSave = new Customer();

		when(customerDao.save(customerToSave)).thenReturn(customerToSave);

		Customer result = customerService.SaveCustomer(customerToSave);

		assertEquals(customerToSave, result);
	}
	
	@Test
	public void testGetAllCustomer() {
		List<Customer> mockCustomers = new ArrayList<>();

		when(customerDao.findAll()).thenReturn(mockCustomers);

		List<Customer> result = customerService.getAllCustomer();

		assertEquals(mockCustomers.size(), result.size());

	}
	@Test
	public void testGetCustomerById() {
		int customerId = 1;
		Customer mockCustomer = new Customer();

		Optional<Customer> optionalCustomer = Optional.of(mockCustomer);

		when(customerDao.findById(customerId)).thenReturn(optionalCustomer);

		Customer result = customerService.getCustomerById(customerId);

		assertEquals(mockCustomer, result);
	}
	
	@Test
	public void testUpdateCustomer() {
		int customerId = 1;
		Customer customerToUpdate = new Customer();

		Optional<Customer> optionalCustomer = Optional.of(customerToUpdate);

		when(customerDao.findById(customerId)).thenReturn(optionalCustomer);

		customerService.updateCustomer(customerId, customerToUpdate);

		verify(customerDao).save(customerToUpdate);
	}
	
	@Test
	public void testDeleteCustomer() {
		int customerId = 1;
		Customer customerToDelete = new Customer();

		Optional<Customer> optionalCustomer = Optional.of(customerToDelete);

		when(customerDao.findById(customerId)).thenReturn(optionalCustomer);

		customerService.deleteCustomer(customerId);

		verify(customerDao).delete(customerToDelete);
	}



}


	