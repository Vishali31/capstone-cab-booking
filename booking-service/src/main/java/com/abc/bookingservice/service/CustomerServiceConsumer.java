package com.abc.bookingservice.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.abc.bookingservice.model.CustomerModel;

@FeignClient(name = "CUSTOMER-SERVICE", url = "http://localhost:8082")
public interface CustomerServiceConsumer {

	@GetMapping("/customer/all")
	List<CustomerModel> getAllCustomers();

}
