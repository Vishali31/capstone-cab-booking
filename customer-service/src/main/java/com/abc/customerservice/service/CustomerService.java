package com.abc.customerservice.service;

import java.util.List;

import com.abc.customerservice.entity.Customer;
import com.abc.customerservice.model.Booking;

public interface CustomerService {

	

	Customer SaveCustomer(Customer customerObj);

	Customer getCustomerById(int customerId);

	boolean updateCustomer(int customerId, Customer customerObj);

	

	List<Customer> getAllCustomer();
	
	Booking getBooking(int bookingId);

	boolean deleteCustomer(int customerId);

	Customer getCustomerByEmail(String email);

	

	

}
