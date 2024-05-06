package com.abc.customerservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.customerservice.entity.Customer;
import com.abc.customerservice.exception.ResourceNotFoundException;
import com.abc.customerservice.model.Booking;
import com.abc.customerservice.model.SaveCustomer;
import com.abc.customerservice.model.ValidateData;
import com.abc.customerservice.service.CustomerService;
import com.abc.customerservice.service.CustomerServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerServiceImpl customerService;

	@GetMapping("/all")
	public List<Customer> fetchAllCustomers() {
		List<Customer> customers = customerService.getAllCustomer();
		return customers;

	}

	@PostMapping("/validate")
	public ResponseEntity<String> validateCustomer(@Valid @RequestBody ValidateData userData) {
		Customer customer = customerService.getCustomerByEmail(userData.getEmail());
		if (customer == null) {
			throw new ResourceNotFoundException("Invalid user !!! ");
		}
		if (!customer.getCustomerPassword().equals(userData.getPassword())) {
			throw new ResourceNotFoundException("Invalid password !!! ");
		}
		return new ResponseEntity<>("User validation successful", HttpStatus.OK);

	}

	@PostMapping("/save")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody SaveCustomer saveCustomer) {
		Customer customerObj = new Customer();

		customerObj.setCustomerName(saveCustomer.getCustomerName());
		customerObj.setCustomerPassword(saveCustomer.getCustomerPassword());
		customerObj.setCustomerAddress(saveCustomer.getCustomerAddress());
		customerObj.setEmail(saveCustomer.getEmail());
		customerObj.setMobile(saveCustomer.getMobile());

		Customer customer = customerService.SaveCustomer(customerObj);
		return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> fetchCustomerDetails(@PathVariable("id") int customerId) {
		Customer customer = customerService.getCustomerById(customerId);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> editCustomer(@PathVariable("id") int customerId, @RequestBody SaveCustomer customer) {
		Customer customerObj = new Customer();
		customerObj.setCustomerName(customer.getCustomerName());
		customerObj.setCustomerId(customerId);
		customerObj.setCustomerAddress(customer.getCustomerAddress());
		customerObj.setCustomerPassword(customer.getCustomerPassword());
		customerObj.setEmail(customer.getEmail());
		customerObj.setMobile(customer.getMobile());

		customerService.updateCustomer(customerId, customerObj);
		return new ResponseEntity<>("updated succesfully", HttpStatus.CREATED);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable("id") int customerId) {
		customerService.deleteCustomer(customerId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/booking/{id}")
	public ResponseEntity<Booking> getBooking(@PathVariable("id") int bookingId) {
		Booking responseEntity = customerService.getBooking(bookingId);
		return new ResponseEntity<>(responseEntity, HttpStatus.OK);
	}

}
