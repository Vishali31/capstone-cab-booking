package com.abc.customerservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abc.customerservice.dao.CustomerDao;
import com.abc.customerservice.entity.Customer;
import com.abc.customerservice.model.Booking;
import com.abc.customerservice.exception.ResourceNotFoundException;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private BookingServiceConsumer bookingServiceConsumer;

	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> customer = customerDao.findAll();
		return customer;
	}

	@Override
	public Customer SaveCustomer(Customer customerObj) {
		Customer customer = customerDao.save(customerObj);
		return customer;
	}

	@Override
	public Customer getCustomerById(int customerId) {
		Optional<Customer> optionalCustomer = customerDao.findById(customerId);
		if (optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("customer not existing with id: " + customerId);
		}
		Customer customer = optionalCustomer.get();
		return customer;
	}

	@Override
	public boolean updateCustomer(int customerId, Customer customerObj) {
		Optional<Customer> optionalCustomer = customerDao.findById(customerId);
		if (optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("driver not existing with id: " + customerId);
		}
		customerDao.save(customerObj);
		return true;
	}

	@Override
	public boolean deleteCustomer(int customerId) {
		Optional<Customer> optionalCustomer = customerDao.findById(customerId);
		if (optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("customer not existing with id: " + customerId);
		}
		Customer driver = optionalCustomer.get();
		customerDao.delete(driver);
		return true;
	}

	@Override
	public Booking getBooking(int bookingId) {
		ResponseEntity<Booking> bookingDetail = bookingServiceConsumer.getBookingDetails(bookingId);
		return bookingDetail.getBody();
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		Customer customer = customerDao.findByEmail(email);

		return customer;
	}

}
